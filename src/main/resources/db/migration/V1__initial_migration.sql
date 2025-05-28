CREATE TABLE addresses
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    street  VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    state   VARCHAR(255) NOT NULL,
    zip     VARCHAR(255) NOT NULL,
    user_id BIGINT       NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE genres
(
    id   TINYINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE novels
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)   NOT NULL,
    `description` LONGTEXT       NOT NULL,
    likes         INT UNSIGNED DEFAULT 0,
    genre_id      TINYINT NULL,
    author_id     BIGINT NOT NULL,
    created_at    DATE NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE chapters
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    novel_id       BIGINT NOT NULL,
    title          VARCHAR(255) NOT NULL,
    content        LONGTEXT NOT NULL,
    chapter_no     INT NOT NULL ,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE profiles
(
    id             BIGINT NOT NULL,
    bio            LONGTEXT NULL,
    phone_number   VARCHAR(15) NULL,
    date_of_birth  date NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE library
(
    novel_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (novel_id, user_id)
);

ALTER TABLE addresses
    ADD CONSTRAINT fk_addresses_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX idx_addresses_user ON addresses (user_id);

ALTER TABLE novels
    ADD CONSTRAINT fk_novels_genres FOREIGN KEY (genre_id) REFERENCES genres (id);

ALTER TABLE novels
    ADD CONSTRAINT fk_novels_author FOREIGN KEY (author_id) REFERENCES users (id);

CREATE INDEX idx_novels_author ON novels (author_id);
CREATE INDEX idx_novels_genre ON novels (genre_id);

ALTER TABLE chapters
    ADD CONSTRAINT fk_chapters_novel FOREIGN KEY (novel_id) REFERENCES novels (id) ON DELETE CASCADE;

CREATE INDEX idx_chapters_novel ON chapters (novel_id);

ALTER TABLE profiles
    ADD CONSTRAINT fk_profiles_user FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE library
    ADD CONSTRAINT fk_bookmarks_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE library
    ADD CONSTRAINT fk_bookmarks_novel FOREIGN KEY (novel_id) REFERENCES novels (id) ON DELETE CASCADE;

CREATE INDEX idx_library_user ON library (user_id);
