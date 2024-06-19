Simple implementation of lisp interpreter in Go

### Features

- Subset of Lisp: basic arithmetic operations, conditionals, and function definitions.
- Parse Input: A parser to read and tokenize Lisp expressions.
- Evaluate Expressions: An evaluator that recursively processes expressions, handling atoms, function calls, and special forms.
- Format function to handle the formatting of the string based on the provided arguments.
- Define Built-in Functions: Functions like addition, subtraction, multiplication, division and conditionals.
- Support User-Defined Functions: Allow users to define their own functions using defun.
- Support for lambda functions, local variables bindings(let) and logical operations(and, or and not)
- Support for basic list operations (car, cdr, cons, length, and append)
- Support for reading and execution of a Lisp script from lisp file
- A REPL: a Read-Eval-Print Loop (REPL) for interactive use.

### Example Interaction
Arithmetic operations
````
> (+ 1 2)
3
> (- 10 4)
6
> (* 3 4)
12
> (/ 8 2)
4
````


Function definitions and conditionals
````
> (defun square (x) (* x x))
<function>

> (square 4)
16

> (if (= 4 4) "equal" "not equal")
equal

> (if (> 10 5) "greater" "less")
greater

> (defun abs (x) (if (< x 0) (- 0 x) x))
<function>

> (abs -7)
7

> (abs 7)
7

````

Local variable bindings
````
> (let ((square (lambda (x) (* x x))))
    (square 5))
25

> (let ((a 10) (b 20))
    (+ a b))
30
> (let ((a 6))
    (if (and (< a 5) (> a 0)) "3 is comprised between 0 and 5" "3 is not comprised between 0 and 5"))
    "3 is not comprised between 0 and 5"
````


Logical operations
````
> (and true true)
true
> (and true false)
false
> (or true false)
true
> (or true true)
true
> (not true)
false
> (not false)
true
````


List operations
````
> (car (list 1 2 3))
1
> (cdr (list 1 2 3))
(2 3)
> (cons 1 (list 2 3))
(1 2 3)
> (length (list 1 2 3 4))
4 
> (append (list 1 2) (list 3 4))
(1 2 3 4)
````

Formatting
````
> (format t "Hello World")
> (let ((hello (lambda (nil)(nil) )))
    (format t "Hello Coding Challenge World"))
"Hello Coding Challenge World"
> (let ((doublen (lambda (n) (* n 2))))
    ((format t "The double of 5 is %d" (doublen 5))))
"The double of 5 is 10"
> (let ((fact (lambda (n)
  (if (<= n 1)
    1
    (* n (fact (- n 1)))))))
    (format t "Factorial of 5 is %d" (fact 5)))
"Factorial of 5 is 120"
````


### Run

- REPL mode
````
go run main.go
````

- File execution mode
````
go run main.go script.lisp
````

### Testing
````
go test -v
````


### known Issues
Only one instruction can be handled at a time. If several instructions are provided one after the other, only the first instruction will be evaluated.
