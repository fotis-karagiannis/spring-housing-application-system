INSERT INTO `user` (`can_apply`, `username`, `password`, `enabled`) VALUES
   (1, 'root', '$2b$10$rCwL7RLYSZ1zbQ2Ycs/uoOTewmZzgsoHmo9VhnR61zFea.gCIPm/u', 1);

INSERT INTO `authorities` (`username`, `authority`) VALUES
   ('root', 'ROLE_ADMIN');