# lexiconis

A Simple Rule Engine in Clojure

## Core Ideas
1. Rules are pure data, expressed in EDN or Clojure maps
2. Ability to define rules based on a spec. All rules must conform to the spec
3. Input is a fact map. Ex - sensor readings or user actions 
4. Output is a vector of results indicating which rules matched
5. Effects (e.g., triggering a fan) are logged or dispatched via multimethods
6. Rules are validated using clojure.spec at definition time 
7. Generative tests can be derived from specs

## Design
1. The input to the rule engine will be the fact map and the rule set
2. The rule engine will verify if the fact map and the rule set are valid
3. If the fact map and rule set are valid, then the rule engine will execute the rules
4. `clojure.spec` is used to verify the fact map and the rule set

## Example
We will be using a home automation example to perform various actions.

1. At 10:30 PM, dim lights, turn off TV, turn on AC
2. If kitchen motion + time between 6AM–8AM → turn on coffee machine
3. If lux level > 10000 AND time between 12pm–4pm → close blinds
4. If TV is on and no motion in room for 30 minutes → turn off TV
5. If smoke detected in kitchen → turn off gas valve + sound alarm + send notification