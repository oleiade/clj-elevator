(ns clj-elevator.messages
    (:require [clj-msgpack.core :as mp])
    (:gen-class))


(defn request [db_uid command & args]
  (mp/pack
    (apply assoc {}
      (interleave ["meta" "uid" "cmd" "args"]
                  [{} db_uid command args]))))

(defn response-header [msg]
  (let [umsg (first (mp/unpack msg))]
    (apply assoc {}
      (interleave [:status :content]
        [(first umsg) (rest umsg)]))))

(defn response [msg]
  (first (mp/unpack msg)))