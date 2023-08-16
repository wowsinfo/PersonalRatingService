"use client";

import { useState } from "react";
import * as PR from "personal-rating";
// Should probably do this else where
type RemoteExpectValue = PR.model.RemoteExpectValue;
const RemoteExpectValue = PR.model.RemoteExpectValue;

import { useEffect } from "react";
import { Repo } from "./repo";
import { Calculator, PersonalRatingResult, PersonalRatingValue } from "./calculator";

const calculator = new Calculator();

export default function Home() {
  /**
   * State hook for storing an array of items.
   * @typeParam T - The type of the items in the array.
   */
  const [items, setItems] = useState<PersonalRatingValue[]>([]);
  const [prInfo, setPrInfo] = useState<PersonalRatingResult | null>(null);
  const [ready, setReady] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  const addItem = () => {
    const newItem = Repo.getInstance().getRandomExpectValue();
    if (!newItem) {
      setError("Failed to get random expect value");
      return;
    }

    // based on the expected value, let's randomise the actual value
    const range = 10000;
    const sign = Math.random() > 0.99 ? 1 : -1;
    const randomise = ((Math.random() * range)) * sign;
    console.log(randomise);
    const randomDamage = Math.max(1000, newItem.averageDamageDealt + randomise);
    const randomFrags = Math.max(0.1, newItem.averageFrags + randomise / 20000);
    const randomWinRate = Math.max(
      15,
      newItem.averageWinRate + randomise / 5000
      );
    console.log(newItem.averageWinRate, randomWinRate);
    const randomBattle = Math.max(10, Math.round(randomise / 10));
    const averageValue = new PR.model.ShipAverageValue(
      randomBattle,
      randomDamage,
      randomWinRate,
      randomFrags
    );

    const rawValue = averageValue.toRaw();
    if (rawValue == null) {
      setError("Failed to convert to raw value");
      return;
    }

    const prValue = new PersonalRatingValue(rawValue, newItem);
    calculator.add(prValue);
    const result = calculator.calculate();
    setPrInfo(result);
    setItems([...items, prValue]);
  };

  useEffect(() => {
    Repo.getInstance()
      .initialize()
      .then(() => {
        setReady(true);
      });
  }, []);

  if (error) {
    return (
      <main className="flex flex-col items-center">
        <p>Error: {error}</p>
      </main>
    );
  }

  if (!ready) {
    return (
      <main className="flex flex-col items-center">
        <p>Loading...</p>
      </main>
    );
  }

  return (
    <main className="flex flex-col items-center">
      <div className="flex justify-center items-center flex-col">
        <h1 className="text-2xl font-bold">Personal Rating Service js</h1>
        <p>{prInfo?.toString()}</p>
        <div className="flex flex-row">
          <p>{prInfo?.prComment.getComment()}</p>
          <p> - </p>
          <p>{prInfo?.apComment.getComment()}</p>
        </div>
      </div>
      {_renderItem(items)}
      <div className="h-16" />
      <button
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full fixed bottom-2"
        onClick={addItem}
      >
        Add a new entry
      </button>
    </main>
  );
}

function _renderItem(items: PersonalRatingValue[]) {
  const renderItem = (item: PersonalRatingValue, index: number) => (
    <div key={index} className="flex flex-col items-center flex-wrap">
      <p className="p-5">Entry {index + 1}</p>
      <p>Battle: {item.battleCount}</p>
      <h1>Expected</h1>
      <p>Damage: {item.expectedDamage}</p>
      <p>Frag: {item.expectedFrags}</p>
      <p>Wins: {item.expectedWins}</p>
      <h1>Actual</h1>
      <p>Damage: {item.actualDamage}</p>
      <p>Frag: {item.actualFrags}</p>
      <p>Wins: {item.actualWins}</p>
    </div>
  );

  return <div>{items.map((item, index) => renderItem(item, index))}</div>;
}
