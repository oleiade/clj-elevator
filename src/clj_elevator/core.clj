(ns clj-elevator.core
    (:require [clj-msgpack.core :as mp]
    		  [org.zeromq.clojure :as zmq])
    (:gen-class))

(defn request [db_uid command & args]
    (mp/pack [db_uid command args]))

(defn response [msg]
    (let [umsg (first (mp/unpack msg))]
        (apply assoc {}
            (interleave [:status :content]
                            [(first umsg) (rest umsg)]))))

(defn sendcmd [req])

(defn connect [req])

(defn listdb [req])

(defn createdb [req])

(defn dropdb [req])

(defn repairdb [req])

(defn Get [req])

(defn Put [req])

(defn Delete [req])

(defn -main [& args]
    (print "It works!"))