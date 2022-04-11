INSERT INTO tb_departament (name, date_register, date_last_edition) VALUES ('Sala de Aula', '2022-03-10 14:30:15.978746', null);
INSERT INTO tb_departament (name, date_register, date_last_edition) VALUES ('Sala do Professor', '2022-03-10 14:31:15.978746', null);
INSERT INTO tb_departament (name, date_register, date_last_edition) VALUES ('Secretaria', '2022-03-10 14:32:15.978746', null);


INSERT INTO tb_category (name, date_register, date_last_edition) VALUES ('Móveis', '2022-03-11 14:43:15.978746', null);
INSERT INTO tb_category (name, date_register, date_last_edition) VALUES ('Informática', '2022-03-11 14:44:15.978746', null);


INSERT INTO tb_patrimony (name, status, description, code, img_url, price, departament, date_register, date_last_edition) VALUES 
	('Notebook Dell', true, 'Core i7, 16gb, SSD 1Tb', '405bbfb8-73eb-406c-99bc-0ab95c50a631', 'https://i.dell.com/is/image/DellContent//content/dam/global-site-design/product_images/dell_client_products/notebooks/inspiron_notebooks/inspiron_15_3511/pdp/notebook-inspiron-15-3511-pdp-gallery-504x350.jpg?qlt=95&fit=constrain,1&hei=350&wid=504&fmt=jpg', 5500.0, 2, '2022-03-14 12:35:15.978746', null);
INSERT INTO tb_patrimony (name, status, description, code, img_url, price, departament, date_register, date_last_edition) VALUES 
	('Mesa em L', true, 'Cor marrom com preto', '3c6a1526-ff73-4807-972a-9f9fde4b0cd9', '', 1000.0, 1, '2022-03-14 14:37:15.978746', null);
	

INSERT INTO tb_patrimony_category (patrimony_id, category_id) VALUES (1, 2);
INSERT INTO tb_patrimony_category (patrimony_id, category_id) VALUES (2, 1);