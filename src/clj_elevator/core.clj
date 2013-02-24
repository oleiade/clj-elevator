(ns clj-elevator.core
    (:require [clj-msgpack.core :as mp]
              [clj-elevator.messages :as msg]
              [org.zeromq.clojure :as zmq])
    (:gen-class))


(defn send-request [connexion request]
  (let [ctx (zmq/make-context 1)
        socket (zmq/make-socket ctx zmq/+xreq+)]
    (zmq/connect socket (:endpoint connexion))
    (zmq/send- socket request)
    (zipmap [:header :response]
            [(msg/response-header (zmq/recv socket))
             (if (.hasReceiveMore socket) (msg/response (zmq/recv socket)) nil)])))


(defn connect [connexion db_name]
  (send-request 
    (connexion (msg/request nil "DBCONNECT" db_name))))


(defn listdb [connexion]
  (send-request
    (connexion (msg/request nil "DBLIST" []))))


(defn createdb [connexion db_name]
  (send-request
    (connexion (msg/request nil "DBCREATE" db_name))))


(defn dropdb [connexion db_name]
  (send-request
    (connexion (msg/request nil "DBDROP" db_name))))


(defn repairdb [connexion db_name]
  (send-request
    (connexion (msg/request nil "DBREPAIR" db_name))))


(defn Get [connexion db_uid key]
  (send-request
    (connexion (msg/request db_uid "GET" key))))


(defn Put [connexion db_uid key value]
  (send-request
    (connexion (msg/request db_uid "PUT" key value))))


(defn Delete [connexion db_uid key]
  (send-request
    (connexion (msg/request db_uid "DELETE" key))))
