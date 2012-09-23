(defproject clj-elevator "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [clj-msgpack "0.2.0"]
                 [org.clojars.vaughan/jzmq "1.1.0"]
                 [org.clojars.mikejs/clojure-zmq "2.0.7-SNAPSHOT"]]
  :native-path "/usr/local/lib"
  :main clj-elevator.core)