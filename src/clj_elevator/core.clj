(ns clj-elevator.core
    (:require [clj-msgpack.core :as mp])
    (:gen-class))

(defn request [db_uid command & args]
    (mp/pack [db_uid command args]))

(defn response [msg]
    (let [umsg (first (mp/unpack msg))]
        (apply assoc {}
            (interleave [:status :content]
                            [(first umsg) (rest umsg)]))))

(defn -main [& args]
    (print "It works!"))