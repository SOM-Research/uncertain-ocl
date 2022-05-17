# uncertain-ocl
An extension of OCL for expressing uncertainty in integrity constraints

UML diagrams can be annotated with OCL (Object Constraint Language) constraints that describe 
the business logic and the integrity constraints of the system being designed. Nevertheless, 
sometimes designers are uncertain about these should constraints should be defined. Maybe they 
don't have enough information, maybe there are alternative definitions or maybe they want to 
postpone the decision until a later stage of the design.

## Language

This package provides an extension of the Object Constraint Language for expressing uncertainty
within an integrity constraint. This extension augments OCL with primitives for describing
alternatives and unknowns within OCL expressions.

Examples of the syntax for "Uncertain OCL":
- A constraint that may or may not become part of the specification
```
[? <constraint> ]
```
- A subexpression that may have one of many alternative values
```
[ <expression>, ..., <expression> ]  
```
- A subexpresion whose value is undecided or not specified at this point   
```
unknown
```
- A numeric constant that may take any value within a given range
``` 
<value>..<value>
```

The directory `examples/` includes several samples of uncertain OCL invariants.

## Tool
    
The tool receives a set of uncertain constraints and produces the list of
concrete realizatsions of the OCL constraints, i.e., considering all potential
alternative ways to define the OCL invariants.

The tool is implemented in Java and used JFlex and CUP (included in the distribution)
to parse the input.

The scripts `compile.sh`and `execute.sh` can be used to compile and execute
the tool. The tool reads the uncertain constraints from the standard input
and emits the concrete OCL constraints to the standard output.

## Contact

This tool is part of joint work by Loli Burgueño, Jordi Cabot and Robert Clarisó
on dealing with uncertainty in OCL specifications.

You can contact the authors at `<rclariso AT uoc.edu>`.
    
