CREATE TABLE if not exists example_table (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO `example_table` (`id`, `name`) VALUES (1, 'example-1'), (2, 'example-2');


CREATE TABLE if not exists player (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists player_progress (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    player_id INT NOT NULL,
    coin INT DEFAULT 5000,
    level INT DEFAULT 1,
    FOREIGN KEY (player_id) REFERENCES player(id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE if not exists reward_claimed (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    player_id INT NOT NULL,
    is_claimed BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (player_id) REFERENCES player(id)
);