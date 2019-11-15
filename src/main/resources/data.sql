INSERT INTO employee(employee_id,employee_name,age) values(1,'山田太郎',30);

INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role) 
  VALUES ('a@a','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田 管理者', '1990-01-01', 28, false, 'ROLE_ADMIN');

  INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role) 
  VALUES ('b@b','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田 普通', '1990-01-01', 28, false, 'ROLE_GENERAL');
  