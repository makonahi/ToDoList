TThis is a fully functional backend for a to-do list web application. It supports all standard CRUD operations — creating, reading, updating, and deleting tasks. The system includes basic authentication, requiring users to log in to access and manage their tasks. A registration feature is also available for creating new user accounts. To ensure security, user passwords are encrypted using BCrypt.

Database Structure:
ToDoList
  -tasks
  -users

-- Table: public.tasks

-- DROP TABLE IF EXISTS public.tasks;

CREATE TABLE IF NOT EXISTS public.tasks
(
    id integer NOT NULL DEFAULT nextval('tasks_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    priority integer,
    creation_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    deadline timestamp(6) without time zone,
    completed boolean DEFAULT false,
    completion_date timestamp(6) without time zone,
    comments text COLLATE pg_catalog."default",
    user_id integer,
    completetion_date timestamp(6) without time zone,
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tasks_priority_check CHECK (priority >= 1 AND priority <= 8)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tasks
    OWNER to postgres;


-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
