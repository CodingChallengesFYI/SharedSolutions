package main

import (
	"fmt"
	"testing"
)

// TestTokenize tests the Tokenize function
func TestTokenize(t *testing.T) {
	tests := []struct {
		input    string
		expected []string
	}{
		{"(define x 10)", []string{"(", "define", "x", "10", ")"}},
		{"(+ 1 2)", []string{"(", "+", "1", "2", ")"}},
		{"(print \"hello world\")", []string{"(", "print", "\"hello world\"", ")"}},
		{"(if (> x 10) \"yes\" \"no\")", []string{"(", "if", "(", ">", "x", "10", ")", "\"yes\"", "\"no\"", ")"}},
	}

	for _, test := range tests {
		result := Tokenize(test.input)
		if !equal(result, test.expected) {
			t.Errorf("Tokenize(%q) = %v, want %v", test.input, result, test.expected)
		}
	}
}

// TestParse tests the Parse function
func TestParse(t *testing.T) {
	tests := []struct {
		tokens   []string
		expected LispValue
	}{
		{[]string{"10"}, &LispNumber{Value: 10}},
		{[]string{"\"hello\""}, &LispString{Value: "hello"}},
		{[]string{"(", "+", "1", "2", ")"}, &LispList{Elements: []LispValue{&LispAtom{Value: "+"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}}}},
	}

	for _, test := range tests {
		result, _, err := Parse(test.tokens)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("Parse(%v) = %v, %v, want %v", test.tokens, result, err, test.expected)
		}
	}
}

// TestEval tests the Eval function
func TestEval(t *testing.T) {
	env := Environment{
		"x": &LispNumber{Value: 10},
	}

	tests := []struct {
		expr     LispValue
		expected LispValue
	}{
		{&LispNumber{Value: 10}, &LispNumber{Value: 10}},
		{&LispString{Value: "hello"}, &LispString{Value: "hello"}},
		{&LispAtom{Value: "x"}, &LispNumber{Value: 10}},
		{&LispList{Elements: []LispValue{&LispAtom{Value: "+"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}}}, &LispNumber{Value: 3}},
	}

	for _, test := range tests {
		result, err := Eval(env, test.expr)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("Eval(%v) = %v, %v, want %v", test.expr, result, err, test.expected)
		}
	}
}

// TestBuiltinFormat tests the builtinFormat function
func TestBuiltinFormat(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispString{Value: "t"}, &LispString{Value: "Hello"}}, &LispString{Value: "Hello"}},
		{[]LispValue{&LispString{Value: "t"}, &LispString{Value: "Factorial of 5 is %d"}, &LispNumber{Value: 120}}, &LispString{Value: "Factorial of 5 is 120"}},
	}

	for _, test := range tests {
		result, err := builtinFormat(env, test.args)
		if err != nil {
			t.Errorf("builtinFormat(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinAdd tests the builtinAdd function
func TestBuiltinAdd(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 1}, &LispNumber{Value: 2}}, &LispNumber{Value: 3}},
		{[]LispValue{&LispNumber{Value: 10}, &LispNumber{Value: 20}}, &LispNumber{Value: 30}},
	}

	for _, test := range tests {
		result, err := builtinAdd(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinAdd(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinSub tests the builtinSub function
func TestBuiltinSub(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 10}, &LispNumber{Value: 5}}, &LispNumber{Value: 5}},
		{[]LispValue{&LispNumber{Value: 20}, &LispNumber{Value: 10}, &LispNumber{Value: 5}}, &LispNumber{Value: 5}},
	}

	for _, test := range tests {
		result, err := builtinSub(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinSub(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinMul tests the builtinMul function
func TestBuiltinMul(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 2}, &LispNumber{Value: 3}}, &LispNumber{Value: 6}},
		{[]LispValue{&LispNumber{Value: 4}, &LispNumber{Value: 5}}, &LispNumber{Value: 20}},
	}

	for _, test := range tests {
		result, err := builtinMul(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinMul(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinDiv tests the builtinDiv function
func TestBuiltinDiv(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
		err      string
	}{
		{[]LispValue{&LispNumber{Value: 10}, &LispNumber{Value: 2}}, &LispNumber{Value: 5}, ""},
		{[]LispValue{&LispNumber{Value: 20}, &LispNumber{Value: 5}}, &LispNumber{Value: 4}, ""},
		{[]LispValue{&LispNumber{Value: 10}, &LispNumber{Value: 0}}, nil, "division by zero"},
	}

	for _, test := range tests {
		result, err := builtinDiv(env, test.args)
		if (err != nil && err.Error() != test.err) || (err == nil && !lispValueEqual(result, test.expected)) {
			t.Errorf("builtinDiv(%v) = %v, %v, want %v, %v", test.args, result, err, test.expected, test.err)
		}
	}
}

// TestBuiltinLt tests the builtinLt function
func TestBuiltinLt(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 1}, &LispNumber{Value: 2}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispNumber{Value: 3}, &LispNumber{Value: 2}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinLt(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinLt(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinLtOrEq tests the builtinLtOrEq function
func TestBuiltinLtOrEq(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 1}, &LispNumber{Value: 2}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispNumber{Value: 3}, &LispNumber{Value: 2}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinLtOrEq(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinLtOrEq(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinGt tests the builtinGt function
func TestBuiltinGt(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 3}, &LispNumber{Value: 2}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispNumber{Value: 1}, &LispNumber{Value: 2}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinGt(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinGt(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinGtOrEq tests the builtinGtOrEq function
func TestBuiltinGtOrEq(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 3}, &LispNumber{Value: 2}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispNumber{Value: 1}, &LispNumber{Value: 2}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinGtOrEq(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinGt(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinEq tests the builtinEq function
func TestBuiltinEq(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 2}, &LispNumber{Value: 2}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispNumber{Value: 2}, &LispNumber{Value: 3}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinEq(env, test.args)
		if err != nil || !lispValueEqual(result, test.expected) {
			t.Errorf("builtinEq(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinDefun tests the builtinDefun function
func TestBuiltinDefun(t *testing.T) {
	env := make(Environment)

	// Test case 1: Correct input
	name := &LispAtom{Value: "my-func"}
	params := &LispList{Elements: []LispValue{&LispAtom{Value: "x"}, &LispAtom{Value: "y"}}}
	body := &LispAtom{Value: "body"}

	args := []LispValue{name, params, body}

	result, err := builtinDefun(env, args)
	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if fn, ok := result.(*LispFunction); !ok {
		t.Fatalf("expected *LispFunction, got %T", result)
	} else {
		if len(fn.Params) != 2 || fn.Params[0].String() != "x" || fn.Params[1].String() != "y" {
			t.Errorf("unexpected function parameters: %v", fn.Params)
		}
		if fn.Body.String() != "body" {
			t.Errorf("unexpected function body: %v", fn.Body)
		}
	}

	if env[name.Value] != result {
		t.Errorf("function not correctly added to environment")
	}

	// Test case 2: Incorrect number of arguments
	args = []LispValue{name, params}
	_, err = builtinDefun(env, args)
	if err == nil || err.Error() != "wrong number of arguments to defun" {
		t.Errorf("expected error for wrong number of arguments, got: %v", err)
	}

	// Test case 3: Invalid function name
	args = []LispValue{params, params, body}
	_, err = builtinDefun(env, args)
	if err == nil || err.Error() != "invalid function name: (x y)" {
		t.Errorf("expected error for invalid function name, got: %v", err)
	}

	// Test case 4: Invalid function parameters
	args = []LispValue{name, body, body}
	_, err = builtinDefun(env, args)
	if err == nil || err.Error() != "invalid function parameters: body" {
		t.Errorf("expected error for invalid function parameters, got: %v", err)
	}
}

// TestBuiltinLambda tests the builtinLambda function
func TestBuiltinLambda(t *testing.T) {
	env := Environment{}

	// Valid test case
	params := &LispList{Elements: []LispValue{&LispAtom{Value: "x"}, &LispAtom{Value: "y"}}}
	body := &LispAtom{Value: "x + y"}
	args := []LispValue{params, body}
	expected := &LispFunction{Params: params.Elements, Body: body, Env: env}

	result, err := builtinLambda(env, args)
	if err != nil {
		t.Fatalf("expected no error, got %v", err)
	}

	if result.String() != expected.String() {
		t.Errorf("expected %v, got %v", expected.String(), result.String())
	}

	// Invalid number of arguments
	invalidArgs := []LispValue{params}
	_, err = builtinLambda(env, invalidArgs)
	if err == nil || err.Error() != "wrong number of arguments to lambda" {
		t.Errorf("expected 'wrong number of arguments to lambda', got %v", err)
	}

	// Invalid parameter type
	invalidParams := []LispValue{&LispAtom{Value: "x"}, body}
	_, err = builtinLambda(env, invalidParams)
	if err == nil || err.Error() != fmt.Sprintf("invalid lambda parameters: %v", invalidParams[0]) {
		t.Errorf("expected 'invalid lambda parameters: %v', got %v", invalidParams[0], err)
	}
}

// TestBuiltinAnd tests the builtinAnd function
func TestBuiltinAnd(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispString{Value: "true"}, &LispString{Value: "false"}}, &LispAtom{Value: "false"}},
		{[]LispValue{&LispString{Value: "true"}, &LispString{Value: "true"}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispString{Value: "false"}, &LispString{Value: "false"}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinAnd(env, test.args)
		if err != nil {
			t.Errorf("builtinAnd(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinOr tests the builtinOr function
func TestBuiltinOr(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispString{Value: "true"}, &LispString{Value: "false"}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispString{Value: "true"}, &LispString{Value: "true"}}, &LispAtom{Value: "true"}},
		{[]LispValue{&LispString{Value: "false"}, &LispString{Value: "false"}}, &LispAtom{Value: "false"}},
	}

	for _, test := range tests {
		result, err := builtinOr(env, test.args)
		if err != nil {
			t.Errorf("builtinOr(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinNot tests the builtinNot function
func TestBuiltinNot(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispString{Value: "true"}}, &LispAtom{Value: "false"}},
		{[]LispValue{&LispString{Value: "false"}}, &LispAtom{Value: "true"}},
	}

	for _, test := range tests {
		result, err := builtinNot(env, test.args)
		if err != nil {
			t.Errorf("builtinNot(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinCar tests the builtinCar function
func TestBuiltinCar(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}}}}, &LispNumber{Value: 1}},
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 2}, &LispNumber{Value: 4}, &LispNumber{Value: 6}}}}, &LispNumber{Value: 1}},
	}

	for _, test := range tests {
		result, err := builtinCar(env, test.args)
		if err != nil {
			t.Errorf("builtinCar(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinCdr tests the builtinCdr function
func TestBuiltinCdr(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}}}}, &LispList{Elements: []LispValue{&LispNumber{Value: 2}, &LispNumber{Value: 3}}}},
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 2}, &LispNumber{Value: 4}, &LispNumber{Value: 6}}}}, &LispList{Elements: []LispValue{&LispNumber{Value: 4}, &LispNumber{Value: 6}}}},
	}

	for _, test := range tests {
		result, err := builtinCdr(env, test.args)
		if err != nil {
			t.Errorf("builtinCdr(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinCons tests the builtinCons function
func TestBuiltinCons(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispNumber{Value: 0}, &LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}}}}, &LispList{Elements: []LispValue{&LispNumber{Value: 0}, &LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}}}},
		{[]LispValue{&LispNumber{Value: 0}, &LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 2}, &LispNumber{Value: 4}, &LispNumber{Value: 6}}}}, &LispList{Elements: []LispValue{&LispNumber{Value: 0}, &LispNumber{Value: 2}, &LispNumber{Value: 4}, &LispNumber{Value: 6}}}},
	}

	for _, test := range tests {
		result, err := builtinCons(env, test.args)
		if err != nil {
			t.Errorf("builtinCons(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinLength tests the builtinLength function
func TestBuiltinLength(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}}}}, &LispNumber{Value: 3}},
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 0}, &LispNumber{Value: 2}, &LispNumber{Value: 4}, &LispNumber{Value: 6}}}}, &LispNumber{Value: 4}},
	}

	for _, test := range tests {
		result, err := builtinLength(env, test.args)
		if err != nil {
			t.Errorf("builtinLength(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// TestBuiltinAppend tests the builtinAppend function
func TestBuiltinAppend(t *testing.T) {
	env := Environment{}

	tests := []struct {
		args     []LispValue
		expected LispValue
	}{
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}}}, &LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 10}, &LispNumber{Value: 11}, &LispNumber{Value: 12}}}}, &LispList{Elements: []LispValue{&LispNumber{Value: 1}, &LispNumber{Value: 2}, &LispNumber{Value: 3}, &LispNumber{Value: 10}, &LispNumber{Value: 11}, &LispNumber{Value: 12}}}},
		{[]LispValue{&LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 4}, &LispNumber{Value: 6}, &LispNumber{Value: 8}}}, &LispList{Elements: []LispValue{&LispAtom{Value: "list"}, &LispNumber{Value: 20}, &LispNumber{Value: 24}, &LispNumber{Value: 30}}}}, &LispList{Elements: []LispValue{&LispNumber{Value: 4}, &LispNumber{Value: 6}, &LispNumber{Value: 8}, &LispNumber{Value: 20}, &LispNumber{Value: 24}, &LispNumber{Value: 30}}}},
	}

	for _, test := range tests {
		result, err := builtinAppend(env, test.args)
		if err != nil {
			t.Errorf("builtinAppend(%v) = %v, %v, want %v", test.args, result, err, test.expected)
		}
	}
}

// Helper functions for tests

func equal(a, b []string) bool {
	if len(a) != len(b) {
		return false
	}
	for i := range a {
		if a[i] != b[i] {
			return false
		}
	}
	return true
}

func lispValueEqual(a, b LispValue) bool {
	switch x := a.(type) {
	case *LispNumber:
		y, ok := b.(*LispNumber)
		return ok && x.Value == y.Value
	case *LispString:
		y, ok := b.(*LispString)
		return ok && x.Value == y.Value
	case *LispAtom:
		y, ok := b.(*LispAtom)
		return ok && x.Value == y.Value
	case *LispList:
		y, ok := b.(*LispList)
		if !ok || len(x.Elements) != len(y.Elements) {
			return false
		}
		for i := range x.Elements {
			if !lispValueEqual(x.Elements[i], y.Elements[i]) {
				return false
			}
		}
		return true
	default:
		return false
	}
}
