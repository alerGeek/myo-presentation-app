package pl.pollub.model.factory.collectors.mousemover;

import lombok.Getter;

import static pl.pollub.model.factory.collectors.mousemover.GlobalMembers.clamp;

@Getter
public class LinearParameter {
  private final float _valueA;
  private final float _valueB;
  private float _input;

  public LinearParameter(float valueA, float valueB, float initialInput) {
    this._valueA = valueA;
    this._valueB = valueB;
    this._input = initialInput;
  }

  public void setInput(float input) { this._input = clamp(input, 0.0F, 1.0F); }

  public float getInput() { return this._input; }

  public float output() { return (1.0F - this._input) * this._valueA + this._input * this._valueB; }
}