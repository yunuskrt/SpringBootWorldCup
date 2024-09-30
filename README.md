# SpringBootWorldCup

### Description

`SpringBootWorldCup` is a REST API for simulating a World Cup game, allowing users to claim rewards, join tournaments, and view leaderboards. It is built using Spring Boot and MySQL, with a daily cronjob running tournaments automatically.

### Features

- **Claim Game Rewards:** Players can claim in-game rewards.
- **Create User:** Allows user creation for participating in tournaments.
- **Update User Level:** Users can level up based on their achievements.
- **Assign Rewards:** Rewards are distributed based on users' success.
- **Enter Tournament:** Users can enter a World Cup tournament.
- **Group Rankings:** Automatically ranks user groups for the tournament.
- **Leaderboards:** Form and retrieve user leaderboards.
- **Cronjob for Daily Tournaments:** A cronjob runs daily to simulate tournaments and rank participants.

### Technologies

- **Backend Framework:** Spring Boot
- **Database:** MySQL
- **Scheduling:** Spring Cronjob for daily tournament simulations
