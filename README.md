# unicorn-team-chat

## Problem
There is no problem the project solves. It's just the university project for credit.

## Description
Chat app with authorization, registration, sending, editing and deleting messages. There is only one room.

## Demo
![screely-1684257986464](https://github.com/plplmax/unicorn-team-chat/assets/50287455/7df94dfd-215d-4605-954f-7c47650e7cd4)
![screely-1684258044030](https://github.com/plplmax/unicorn-team-chat/assets/50287455/503348a7-6e56-43ac-8907-a3a5711ec995)
![screely-1684258075202](https://github.com/plplmax/unicorn-team-chat/assets/50287455/2cbe95b1-aec5-4143-894e-c4f3a5c77685)
![screely-1684258098275](https://github.com/plplmax/unicorn-team-chat/assets/50287455/1838395b-4836-4289-ae0d-5cd8d64ebeb7)
![screely-1684258118025](https://github.com/plplmax/unicorn-team-chat/assets/50287455/3b7ce7e8-7787-4dc7-a349-5bacfd15fac4)
<img width="300" src="https://github.com/plplmax/unicorn-team-chat/assets/50287455/bda4fbbe-d3de-4157-b4d9-f758df956185">

## Frontend

Built with [Vue 3](https://vuejs.org/), [Vue Router](https://router.vuejs.org/),
[Vue Toastification](https://github.com/Maronato/vue-toastification), [Axios](https://github.com/axios/axios),
[jwt-decode](https://github.com/auth0/jwt-decode)

```sh
cd frontend && npm install
```

#### Compile and Hot-Reload for Development

```sh
npm run dev
```

#### Compile and Minify for Production

```sh
npm run build
```

#### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```

## Backend

Built with [Ktor](https://ktor.io/), [Web Sockets](https://ktor.io/docs/websocket.html), [JWT](https://ktor.io/docs/jwt.html),
[PostgreSQL](https://www.postgresql.org/), [Exposed](https://github.com/JetBrains/Exposed), [Argon2](https://github.com/phxql/argon2-jvm),
[Docker Compose](https://docs.docker.com/compose/)

```sh
cd backend
```

```sh
docker-compose up -d
```

```sh
./gradlew run
```
