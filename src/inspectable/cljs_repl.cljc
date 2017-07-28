(ns inspectable.cljs-repl)

(defmacro why
  "Tries to run the form and detect clojure.spec fails.
  If form returns ex-data or throws and expression containing it as returned by (clojure.spec/ex-data ...)
  opens a graphical interface trying to explain what went wrong."
  [form]
  `(try
     (let [result# ~form]
       (if (spec-ex-data? result#)
         (pretty-explain nil result#)
         result#))
     (catch js/Error e#
       (if (spec-ex-data? e#)
         (pretty-explain (fn-symbol-from-ex e#) (ex-data e#))
         (throw e#)))))
 

(defmacro a []
  `(+ 1 1))
