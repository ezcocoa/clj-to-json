(ns map-to-json.core
  (:require [cheshire.core :refer [generate-string]])
  (:gen-class :main true))

(defn format-time [timestamp]
  (-> "yyyyMMdd"
      (java.text.SimpleDateFormat.)
      (.format timestamp)))

(defn write [file-path s]
  (with-open [wtr (clojure.java.io/writer file-path)]
    (.write wtr s)))

(defn -main
  [& args]
  (if (or (= (first args) "help"))
    (prn "usage: file path")
    (if (> (count args) 0)
      (try
        (if-let [s (generate-string (eval (read-string (slurp (first args)))))]
          (write (str "./result_" (format-time (new java.util.Date)) ".json") s))
        (catch Exception e (.getMessage e ))))))
