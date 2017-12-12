# Akka-Http and React starter app #
A starter application written in Scala and ES6.
It uses akka-http as a backend and react as frontend.


Backend

- Akka-Http
- h2 embedded database to store data
- scalikejdbc to access database
- scalaz OptionT, Either


Frontend

- react
- redux state container
- react router declarative routing for react
- babel for ES6 and ES7 magic
- webpack for bundling
- http-proxy-middleware to proxy 
- redux thunk - used in async actions
- axios promise based HTTP client

## Run

Start frontend server:

```
$ ./start_frontend.sh
```

Start backend server:

```
$ ./start_backend.sh
```
