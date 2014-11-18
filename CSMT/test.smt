(define-fun  IntPointer  1)
(define-fun  FloatPointer  2)

(declare-sort String 0)
(declare-fun length ( String ) Int )
(declare-fun charOf (String Int) Int)

(declare-fun valueOf (IntPointer) Int)
(declare-fun valueOf (FloatPointer) Real)

(declare-fun addressOf (IntPointer) Int)
(declare-fun addressOf (FloatPointer) Real)

(declare-fun a () IntPointer)
(declare-fun b () FloatPointer)
(assert(= a b))
(declare-const c Int)

(check-sat)