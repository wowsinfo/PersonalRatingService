import * as PR from "personal-rating";
type PersonalRatingCalculator = PR.usecase.PersonalRatingCalculator;
const PersonalRatingCalculator = PR.usecase.PersonalRatingCalculator;
export type PersonalRatingValue = PR.model.PersonalRatingValue;
export const PersonalRatingValue = PR.model.PersonalRatingValue;
export type PersonalRatingResult = PR.model.PersonalRatingResult;

export class Calculator {
  private calculator: PersonalRatingCalculator = new PersonalRatingCalculator();

  public add(item: PersonalRatingValue) {
    this.calculator?.addItem(item);
  }

  public calculate(): PersonalRatingResult {
    return this.calculator?.calculate();
  }
}
