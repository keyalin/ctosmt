(declare-const a Int)
(assert(= a
( declare-const out Int)
(assert(= out
(declare-const c Int)
(assert (= c (* 2 a) ))
(assert ( = out c))
(check-sat)
