push-demo
=========

[Datomic](http://www.datomic.com/) has an interesting feature called the [Transaction Report Queue](http://blog.datomic.com/2013/10/the-transaction-report-queue.html), which allows for a client (or peer) application to monitor changes to a database.

The Clojure code in this repo shows how to use the transaction report queue to monitor transactions, and push changes to a web page using websockets.

Technologies used:

* Clojure and ClojureScript
* Ring with Compojure
* [Datomic](http://www.datomic.com/)
* [Sente](https://github.com/ptaoussanis/sente) for websocket communication
* [Reagent](https://github.com/holmsand/reagent) (a ClojureScript interface for Facebook's React library) for rendering the UI

Running the Application
=======================

1. Install Datomic
2. ``lein run``
3. Open http://localhost:3000
