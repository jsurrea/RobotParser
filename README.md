# Usage

Run `Parser.java` with a command line argument specifying the path to the text file that contains the program, for example `test-cases\Complex.txt`. You can also start a REPL session by executing `Parser.java` without any arguments. The REPL session is also available for the class `Lexer.java`.

# Formal Grammar Definition

## Tokens

### Characters

- $\textcolor{blue}{[} \space:$ Opening Bracket
- $\textcolor{blue}{]} \space:$ Closing Bracket
- $\textcolor{blue}{:} \space:$ Colon
- $\textcolor{blue}{;} \space:$ Semicolon
- $\textcolor{blue}{,} \space:$ Comma
- $\textcolor{blue}{|} \space:$ Pipe

### Keywords

- $\textcolor{blue}{Robot\_R} \space$
- $\textcolor{blue}{vars} \space$
- $\textcolor{blue}{procs} \space$
- $\textcolor{blue}{if} \space$
- $\textcolor{blue}{then} \space$
- $\textcolor{blue}{else} \space$
- $\textcolor{blue}{while} \space$
- $\textcolor{blue}{do} \space$
- $\textcolor{blue}{repeat} \space$

### Commands

- $\textcolor{blue}{assignTo} \space$
- $\textcolor{blue}{goto} \space$
- $\textcolor{blue}{move} \space$
- $\textcolor{blue}{turn} \space$
- $\textcolor{blue}{face} \space$
- $\textcolor{blue}{put} \space$
- $\textcolor{blue}{pick} \space$
- $\textcolor{blue}{moveToThe} \space$
- $\textcolor{blue}{moveInDir} \space$
- $\textcolor{blue}{jumpToThe} \space$
- $\textcolor{blue}{jumpInDir} \space$
- $\textcolor{blue}{nop} \space$

### Conditions

- $\textcolor{blue}{facing} \space$
- $\textcolor{blue}{canPut} \space$
- $\textcolor{blue}{canPick} \space$
- $\textcolor{blue}{canMoveInDir} \space$
- $\textcolor{blue}{canJumpInDir} \space$
- $\textcolor{blue}{canMoveToThe} \space$
- $\textcolor{blue}{canJumpToThe} \space$
- $\textcolor{blue}{not} \space$

### Special Words

- $\textcolor{blue}{north} \space$
- $\textcolor{blue}{south} \space$
- $\textcolor{blue}{east} \space$
- $\textcolor{blue}{west} \space$
- $\textcolor{blue}{front} \space$
- $\textcolor{blue}{right} \space$
- $\textcolor{blue}{left} \space$
- $\textcolor{blue}{back} \space$
- $\textcolor{blue}{around} \space$
- $\textcolor{blue}{chips} \space$
- $\textcolor{blue}{balloons} \space$

### Others (see implementation)

- $\textcolor{blue}{ \langle name \rangle }:$ Identifiers
- $\textcolor{blue}{ \langle number \rangle }:$ Numbers
- $\textcolor{blue}{ \langle illegal \rangle }:$ Illegal Character
- $\textcolor{blue}{ \langle EoF \rangle }:$ End Of File

## Variables

- $\textcolor{black}{ \langle Program \rangle }:$ A Program for the Robot
- $\textcolor{black}{ \langle Vars \rangle }:$ Declaration of Variables
- $\textcolor{black}{ \langle Procs \rangle }:$ Procedure Declaration
- $\textcolor{black}{ \langle Proc \rangle }:$ Procedure Definition
- $\textcolor{black}{ \langle Block \rangle }:$ Block of Instructions
- $\textcolor{black}{ \langle Instructions \rangle }:$ Instructions separated by Semicolons
- $\textcolor{black}{ \langle Instruction \rangle }:$ Instruction Structure
- $\textcolor{black}{ \langle Control \rangle }:$ Control Structure
- $\textcolor{black}{ \langle Cond \rangle }:$ Conditional Structure
- $\textcolor{black}{ \langle Loop \rangle }:$ Loop Structure
- $\textcolor{black}{ \langle Repeat \rangle }:$ RepeatTimes Structure
- $\textcolor{black}{ \langle Call \rangle }:$ Procedure Call
- $\textcolor{black}{ \langle Cmd \rangle }:$ Command
- $\textcolor{black}{ \langle Cnd \rangle }:$ Condition
- $\textcolor{black}{ \langle NameList \rangle }:$ Names separated by Commas 
- $\textcolor{black}{ \langle ArgsList \rangle }:$ Arguments separated by Commas 
- $\textcolor{black}{ \langle Arg \rangle }:$ Argument Structure

## Production Rules

The initial symbol is $\textcolor{black}{ \langle Program \rangle }$
Terminals (tokens) are shown in $\textcolor{blue}{blue}$.
Non-Terminals (variables) are shown in $\textcolor{black}{black}$.
BNF notation is shown in $\textcolor{red}{red}$:
- $\textcolor{red}{\rightarrow} \space :$ represents a production rule.
- $\textcolor{red}{(\space)?} \space :$ means optional.
- $\textcolor{red}{(\space)*} \space :$ means zero or more.
- $\textcolor{red}{\space |} \space :$ means OR.

### General

- $\textcolor{black}{ \langle Program \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{Robot\_R} \space \textcolor{red}{(} \textcolor{black}{ \langle Vars \rangle } \textcolor{red}{)?} \space \textcolor{red}{(} \textcolor{black}{ \langle Procs \rangle } \textcolor{red}{)?} \space \textcolor{black}{ \langle Block \rangle } \space \textcolor{blue}{ \langle EoF \rangle }$

- $\textcolor{black}{ \langle Vars \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{vars} \space \textcolor{black}{ \langle NameList \rangle }$

- $\textcolor{black}{ \langle Procs \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{procs} \space \textcolor{black}{ \langle Proc \rangle } \space \textcolor{red}{(} \textcolor{black}{ \langle Proc \rangle } \textcolor{red}{)*}$

- $\textcolor{black}{ \langle Proc \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{ \langle name \rangle } \space \textcolor{blue}{[} \space \textcolor{blue}{|} \space \textcolor{red}{(} \textcolor{black}{ \langle NameList \rangle } \space \textcolor{red}{)?} \space\textcolor{blue}{|} \space \textcolor{black}{ \langle Instructions \rangle } \space \textcolor{blue}{]} \space$

- $\textcolor{black}{ \langle Block \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{[} \space \textcolor{black}{ \langle Instructions \rangle } \space \textcolor{blue}{]} \space$

- $\textcolor{black}{ \langle Instructions \rangle } \textcolor{red}{\rightarrow} \textcolor{black}{ \langle Instruction \rangle } \space \textcolor{red}{(} \space \textcolor{blue}{;} \space \textcolor{black}{ \langle Instruction \rangle } \textcolor{red}{)*}$ 

- $\textcolor{black}{ \langle Instruction \rangle } \textcolor{red}{\rightarrow} \textcolor{black}{ \langle Cmd \rangle } \space \textcolor{red}{|} \space \textcolor{black}{ \langle Control \rangle } \space \textcolor{red}{|} \space \textcolor{black}{ \langle Call \rangle }$

- $\textcolor{black}{ \langle Control \rangle } \textcolor{red}{\rightarrow} \textcolor{black}{ \langle Cond \rangle } \space\textcolor{red}{|}\space \textcolor{black}{ \langle Loop \rangle } \space\textcolor{red}{|}\space \textcolor{black}{ \langle Repeat \rangle }$

- $\textcolor{black}{ \langle Cond \rangle } \textcolor{red}{\rightarrow}  \textcolor{blue}{if} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Cnd \rangle } \space \space \textcolor{blue}{then} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Block \rangle } \space \space \textcolor{blue}{else} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Block \rangle }$

- $\textcolor{black}{ \langle Loop \rangle } \textcolor{red}{\rightarrow} \space \textcolor{blue}{while} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Cnd \rangle } \space \space \textcolor{blue}{do} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Block \rangle }$

- $\textcolor{black}{ \langle Repeat \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{repeat} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \textcolor{black}{ \langle Block \rangle }$

- $\textcolor{black}{ \langle Call \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{ \langle name \rangle } \space \textcolor{blue}{:} \space \textcolor{red}{(} \textcolor{black}{ \langle ArgsList \rangle } \textcolor{red}{)?}$

- $\textcolor{black}{ \langle NameList \rangle }  \textcolor{red}{\rightarrow} \textcolor{blue}{ \langle name \rangle } \space \textcolor{red}{(} \space \textcolor{blue}{,} \space \textcolor{blue}{ \langle name \rangle } \textcolor{red}{)*}$

- $\textcolor{black}{ \langle ArgsList \rangle }  \textcolor{red}{\rightarrow} \textcolor{black}{ \langle Arg \rangle } \space \textcolor{red}{(} \space \textcolor{blue}{,} \space \textcolor{black}{ \langle Arg \rangle }\textcolor{red}{)*}$

- $\textcolor{black}{ \langle Arg \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{ \langle name \rangle } \space\textcolor{red}{|}\space \textcolor{blue}{ \langle number \rangle }$

### Commands

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{assignTo} \space \textcolor{blue}{:} \space \textcolor{blue}{ \langle number \rangle } \space \textcolor{blue}{,} \space \textcolor{blue}{\langle name \rangle}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{goto} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{black}{ \langle Arg \rangle }$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{move} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle }$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{turn} \space \textcolor{blue}{:} \space \textcolor{red}{(} \space \textcolor{blue}{left} \space \textcolor{red}{|} \space \textcolor{blue}{right} \space \textcolor{red}{|} \space \textcolor{blue}{around} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{face} \space \textcolor{blue}{:} \space \textcolor{red}{(} \space \textcolor{blue}{north} \space \textcolor{red}{|} \space \textcolor{blue}{south} \space \textcolor{red}{|} \space \textcolor{blue}{east} \space \textcolor{red}{|} \space \textcolor{blue}{west} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{put} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{balloons} \space \textcolor{red}{|} \space \textcolor{blue}{chips} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{pick} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{balloons} \space \textcolor{red}{|} \space \textcolor{blue}{chips} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{moveToThe} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{front} \space \textcolor{red}{|} \space \textcolor{blue}{right} \space \textcolor{red}{|} \space \textcolor{blue}{left} \space \textcolor{red}{|} \space \textcolor{blue}{back} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{moveInDir} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{north} \space \textcolor{red}{|} \space \textcolor{blue}{south} \space \textcolor{red}{|} \space \textcolor{blue}{west} \space \textcolor{red}{|} \space \textcolor{blue}{east} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{jumpToThe} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{front} \space \textcolor{red}{|} \space \textcolor{blue}{right} \space \textcolor{red}{|} \space \textcolor{blue}{left} \space \textcolor{red}{|} \space \textcolor{blue}{back} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{jumpInDir} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{north} \space \textcolor{red}{|} \space \textcolor{blue}{south} \space \textcolor{red}{|} \space \textcolor{blue}{west} \space \textcolor{red}{|} \space \textcolor{blue}{east} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cmd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{nop} \space \textcolor{blue}{:} \space$

### Conditions

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{facing} \space \textcolor{blue}{:} \space \textcolor{red}{(} \space \textcolor{blue}{north} \space \textcolor{red}{|} \space \textcolor{blue}{south} \space \textcolor{red}{|} \space \textcolor{blue}{east} \space \textcolor{red}{|} \space \textcolor{blue}{west} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{canPut} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{balloons} \space \textcolor{red}{|} \space \textcolor{blue}{chips} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{canPick} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{balloons} \space \textcolor{red}{|} \space \textcolor{blue}{chips} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{canMoveInDir} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{north} \space \textcolor{red}{|} \space \textcolor{blue}{south} \space \textcolor{red}{|} \space \textcolor{blue}{west} \space \textcolor{red}{|} \space \textcolor{blue}{east} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{canJumpInDir} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{north} \space \textcolor{red}{|} \space \textcolor{blue}{south} \space \textcolor{red}{|} \space \textcolor{blue}{west} \space \textcolor{red}{|} \space \textcolor{blue}{east} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{canMoveToThe} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{front} \space \textcolor{red}{|} \space \textcolor{blue}{right} \space \textcolor{red}{|} \space \textcolor{blue}{left} \space \textcolor{red}{|} \space \textcolor{blue}{back} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{canJumpToThe} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Arg \rangle } \space \textcolor{blue}{,} \space \textcolor{red}{(} \space \textcolor{blue}{front} \space \textcolor{red}{|} \space \textcolor{blue}{right} \space \textcolor{red}{|} \space \textcolor{blue}{left} \space \textcolor{red}{|} \space \textcolor{blue}{back} \space \textcolor{red}{)}$

- $\textcolor{black}{ \langle Cnd \rangle } \textcolor{red}{\rightarrow} \textcolor{blue}{not} \space \textcolor{blue}{:} \space \textcolor{black}{ \langle Cnd \rangle }$