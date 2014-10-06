(declare-const a Int)
(assert(= a
(declare-const b Int)
(assert(= b
( declare-const out Int)
(assert(= out
(declare-const c Int)
(assert (= c (* a b) ))
(assert ( = out c))
(check-sat)
