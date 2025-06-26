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