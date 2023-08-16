import * as PR from "personal-rating";
const RemoteDataService = PR.service.RemoteDataServiceJs;
type RemoteDataService = PR.service.RemoteDataServiceJs;
const PersonalRatingReader = PR.usecase.PersonalRatingReader;
type PersonalRatingReader = PR.usecase.PersonalRatingReader;
type RemoteExpectValue = PR.model.RemoteExpectValue;

/**
 * A singleton class which provides access to the personal rating data either from remote or cached
 */
export class Repo {
  private static keys = {
    personalRating: "personalRating",
  };

  private static instance: Repo;
  private reader: PR.Nullable<PersonalRatingReader>;
  private allKeys: string[] = [];

  private constructor() {}

  public static getInstance(): Repo {
    if (!Repo.instance) {
      Repo.instance = new Repo();
    }
    return Repo.instance;
  }

  public async initialize() {
    const cached = localStorage.getItem(Repo.keys.personalRating);
    if (cached) {
      this.reader = PersonalRatingReader.Companion.fromString(cached);
      console.log("Using cached personal rating data");
    } else {
      const remote = new RemoteDataService();
      console.log("Using remote personal rating data");
      const remoteString = await remote.getRemoteStringPromise();
      localStorage.setItem("personalRating", remoteString);
      this.reader = PersonalRatingReader.Companion.fromString(remoteString);
    }

    this.allKeys = this.reader?.getAllKeys() || [];
  }

  public getExpectedValue(key: string): PR.Nullable<RemoteExpectValue> {
    return this.reader?.getExpectedValue(key);
  }

  public getRandomExpectValue(): PR.Nullable<RemoteExpectValue> {
    length = this.allKeys.length;
    if (length === 0) return null;
    // need to improve on this
    while (true) {
        const index = Math.floor(Math.random() * length);
        const key = this.allKeys[index];
        const expectValue = this.getExpectedValue(key);
        if (expectValue) return expectValue;
    }
  }
}
