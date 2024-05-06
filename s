                                      Table "public.users"
   Column   |          Type          | Collation | Nullable |              Default              
------------+------------------------+-----------+----------+-----------------------------------
 id         | bigint                 |           | not null | nextval('users_id_seq'::regclass)
 email      | character varying(50)  |           |          | 
 password   | character varying(120) |           |          | 
 username   | character varying(20)  |           |          | 
 enabled    | boolean                |           |          | 
 first_name | character varying(20)  |           |          | 
 last_name  | character varying(20)  |           |          | 
 vat        | character varying(9)   |           |          | 
Indexes:
    "users_pkey" PRIMARY KEY, btree (id)
    "uk_frmekpavt29kfj098jahsy0et" UNIQUE CONSTRAINT, btree (vat)
Referenced by:
    TABLE "user_roles" CONSTRAINT "fkhfh9dx7w3ubf1co1vdev94g3f" FOREIGN KEY (user_id) REFERENCES users(id)
    TABLE "owner" CONSTRAINT "fksk80mgxau4fje7xby9j990rb" FOREIGN KEY (user_id) REFERENCES users(id)

