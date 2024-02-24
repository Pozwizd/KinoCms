use kino_cms;
INSERT INTO User (name, surname, nickname, email, address, password, card_number, language, gender, phone_number,
                  date_of_birth, city)
VALUES ('John', 'Doe', 'johndoe', 'user1@example.com', '123 Main St', '0000', '1234567890123456', 'Русский', 'MALE',
        '1234567890', '1990-01-01', 'New York'),
       ('Jane', 'Smith', 'janesmith', 'user2@example.com', '456 Elm St', '0000', '9876543210987654', 'Русский',
        'FEMALE', '9876543210', '1995-02-15', 'Paris');

insert into main_page(id,
                      phone_number,
                      phone_number2,
                      seo_description,
                      seo_keywords,
                      seo_title,
                      seo_url,
                      status,
                      date_of_created)
VALUES (1,
        '1234567890',
        '9876543210',
        'This is the SEO description of the main page.',
        'This is the SEO keywords of the main page.',
        'This is the SEO title of the main page.',
        'main-page',
        true,
        '2020-01-01');


insert into page (description,
                  main_image,
                  name,
                  seo_description,
                  seo_keywords,
                  seo_title,
                  seo_url,
                  status,
                  date_of_created)
VALUES ('This is the description of the first page.',
        'This is the main image of the first page.',
        'О кинотеатре',
        'This is the SEO description of the first page.',
        'This is the SEO keywords of the first page.',
        'This is the SEO title of the first page.',
        'first-page',
        true,
        '2020-01-01'),
       ('This is the description of the second page.',
        'This is the main image of the second page.',
        'Кафе - Бар',
        'This is the SEO description of the second page.',
        'This is the SEO keywords of the second page.',
        'This is the SEO title of the second page.',
        'second-page',
        true,
        '2020-01-01'),
       ('This is the description of the first page.',
        'This is the main image of the first page.',
        'Vip - зал',
        'This is the SEO description of the first page.',
        'This is the SEO keywords of the first page.',
        'This is the SEO title of the first page.',
        'first-page',
        true,
        '2020-01-01'),
       ('This is the description of the first page.',
        'This is the main image of the first page.',
        'Реклама',
        'This is the SEO description of the first page.',
        'This is the SEO keywords of the first page.',
        'This is the SEO title of the first page.',
        'first-page',
        true,
        '2020-01-01'),
       ('This is the description of the first page.',
        'This is the main image of the first page.',
        'Детская комната',
        'This is the SEO description of the first page.',
        'This is the SEO keywords of the first page.',
        'This is the SEO title of the first page.',
        'first-page',
        true,
        '2020-01-01');

insert into contact_page(id, date_of_created, link_video, name, seo_description,
                         seo_keywords, seo_title, seo_url, status)
VALUES (1,
        '2020-01-01',
        'https://www.youtube.com/embed/dQw4w9WgXcQ',
        'Контакты',
        'This is the SEO description of the contact page.',
        'This is the SEO keywords of the contact page.',
        'This is the SEO title of the contact page.',
        'contact-page',
        true);

insert into banner_block(id, status, time_change)
values (1, true, 3);

insert into banner_block_for_news_and_stocks(id, status_block_banner_for_news_and_stocks,
                                             time_change_block_banner_for_news_and_stocks)
values (1, true, 3);



insert into stock (id,
                   date_created,
                   date_posting,
                   description,
                   link_video,
                   main_image,
                   name,
                   seo_description,
                   seo_keywords,
                   seo_title,
                   seo_url,
                   status)
    value (1,
           '2020-01-01',
           '2020-01-01',
           'This is the description of the first stock.',
           'https://www.youtube.com/embed/dQw4w9WgXcQ',
           'This is the main image of the first stock.',
           'First Stock',
           'This is the SEO description of the first stock.',
           'This is the SEO keywords of the first stock.',
           'This is the SEO title of the first stock.',
           'first-stock',
           true),
    (2,
     '2020-01-01',
     '2020-01-01',
     'This is the description of the second stock.',
     'https://www.youtube.com/embed/dQw4w9WgXcQ',
     'This is the main image of the second stock.',
     'Second Stock',
     'This is the SEO description of the second stock.',
     'This is the SEO keywords of the second stock.',
     'This is the SEO title of the second stock.',
     'second-stock',
     true);

insert into news (id,
                  date_created,
                  date_posting,
                  description,
                  link_video,
                  main_image,
                  name,
                  seo_description,
                  seo_keywords,
                  seo_title,
                  seo_url,
                  status)
    VALUE (
           1,
           '2020-01-01',
           '2020-01-01',
           'This is the description of the first news.',
           'https://www.youtube.com/embed/dQw4w9WgXcQ',
           'This is the main image of the first news.',
           'First News',
           'This is the SEO description of the first news.',
           'This is the SEO keywords of the first news.',
           'This is the SEO title of the first news.',
           'first-news',
           true
    ),
    (
     2,
     '2020-01-01',
     '2020-01-01',
     'This is the description of the first news.',
     'https://www.youtube.com/embed/dQw4w9WgXcQ',
     'This is the main image of the first news.',
     'First News',
     'This is the SEO description of the first news.',
     'This is the SEO keywords of the first news.',
     'This is the SEO title of the first news.',
     'first-news',
     true
        );

insert into cinemas (id, conditions,
                     description, logo_path,
                     name, seo_description,
                     seo_keywords, seo_title,
                     seo_url,
                     top_banner)
VALUES (1,
        'This is the description of the first cinema.',
        'This is the logo of the first cinema.',
        'first-cinema',
        'First Cinema',
        'This is the SEO description of the first cinema.',
        'This is the SEO keywords of the first cinema.',
        'This is the SEO title of the first cinema.',
        'first-cinema',
        'This is the top banner of the first cinema.'),
       (2,
        'This is the description of the second cinema.',
        'This is the logo of the second cinema.',
        'second-cinema',
        'Second Cinema',
        'This is the SEO description of the second cinema.',
        'This is the SEO keywords of the second cinema.',
        'This is the SEO title of the second cinema.',
        'second-cinema',
        'This is the top banner of the second cinema.');

insert into hall (id,
                  date_created,
                  description,
                  hall_number,
                  logo_path,
                  seo_description,
                  seo_keywords,
                  seo_title,
                  seo_url,
                  top_banner,
                  url_scheme_image_hall,
                  cinema_id)
VALUES (1,
        '2020-01-01',
        'This is the description of the first hall.',
        '1',
        null,
        'This is the SEO description of the first hall.',
        'This is the SEO keywords of the first hall.',
        'This is the SEO title of the first hall.',
        'first-hall',
        'This is the top banner of the first hall.',
        'This is the url scheme image of the first hall.',
        1),
       (2,
        '2020-01-01',
        'This is the description of the second hall.',
        '2',
        null,
        'This is the SEO description of the second hall.',
        'This is the SEO keywords of the second hall.',
        'This is the SEO title of the second hall.',
        'second-hall',
        'This is the top banner of the second hall.',
        'This is the url scheme image of the second hall.',
        1);

insert into film (id,name,
                  description,
                  start_premiere_date,
                  end_premiere_date,
                  link_trailer,
                  main_image,
                  seo_description,
                  seo_keywords,
                  seo_title,
                  seo_url)
VALUES (1
       ,'Фильм 1'
       ,'Описание фильма 1'
       ,'2023-08-01'
       ,'2023-08-28'
       ,'http/'
       ,'Описание фильма 1'
       ,'Ключевые слова фильма 1'
       ,'Заголовок фильма 1'
       ,'film-1',
        'url'
       ),

       (2
       ,'Фильм 2'
       ,'Описание фильма 2'
       ,'2023-09-01'
       ,'2023-09-28'
       ,'http/'
       ,'Описание фильма 2'
       ,'Ключевые слова фильма 2'
       ,'Заголовок фильма 2'
       ,'film-2',
        'url'
       ),
       (3
       ,'Фильм 3'
       ,'Описание фильма 3'
       ,'2023-10-01'
       ,'2023-10-28'
       ,'http/'
       ,'Описание фильма 3'
       ,'Ключевые слова фильма 3'
       ,'Заголовок фильма 3'
       ,'film-3',
        'url'
       ),
       (4
       ,'Фильм 4'
       ,'Описание фильма 4'
       ,'2023-11-01'
       ,'2023-11-28'
       ,'http/'
       ,'Описание фильма 4'
       ,'Ключевые слова фильма 4'
       ,'Заголовок фильма 4'
       ,'film-4',
        'url'
       ),
       (5
       ,'Фильм 5'
       ,'Описание фильма 5'
       ,'2023-12-01'
       ,'2023-12-28'
       ,'http/'
       ,'Описание фильма 5'
       ,'Ключевые слова фильма 5'
       ,'Заголовок фильма 5'
       ,'film-5',
        'url'
       ),
       (6
       ,'Фильм 6'
       ,'Описание фильма 6'
       ,'2024-01-01'
       ,'2024-01-28'
       ,'http/'
       ,'Описание фильма 6'
       ,'Ключевые слова фильма 6'
       ,'Заголовок фильма 6'
       ,'film-6',
        'url'
       ),
       (7
       ,'Фильм 7'
       ,'Описание фильма 7'
       ,'2024-02-01'
       ,'2024-02-28'
       ,'http/'
       ,'Описание фильма 7'
       ,'Ключевые слова фильма 7'
       ,'Заголовок фильма 7'
       ,'film-7',
        'url'
       ),
       (8
       ,'Фильм 8'
       ,'Описание фильма 8'
       ,'2024-03-01'
       ,'2024-03-28'
       ,'http/'
       ,'Описание фильма 8'
       ,'Ключевые слова фильма 8'
       ,'Заголовок фильма 8'
       ,'film-8',
        'url'
       ),
       (9
       ,'Фильм 9'
       ,'Описание фильма 9'
       ,'2024-04-01'
       ,'2024-04-28'
       ,'http/'
       ,'Описание фильма 9'
       ,'Ключевые слова фильма 9'
       ,'Заголовок фильма 9'
       ,'film-9',
        'url'
       ),
       (10
           ,'Фильм 10'
           ,'Описание фильма 10'
           ,'2024-05-01'
           ,'2024-05-28'
           ,'http/'
           ,'Описание фильма 10'
               ,'Ключевые слова фильма 10'
               ,'Заголовок фильма 10'
               ,'film-10',
                'url'
           );

insert into film_type (film_id, type)
VALUES (1, 'Threedimensional'),
       (1, 'Twodimensional'),
       (2, 'IMAX');

insert into banner_background(id, is_default, url)
VALUES (1, true, null);

insert into banner(id, path_image, title, url, banner_block_id)
VALUES (1, 'url', 'title', 'url', 1),
       (2, 'url', 'title', 'url', 1),
       (3, 'url', 'title', 'url', 1);

insert into banner_for_news_and_stocks(id, path_image, title, url, banner_block_for_news_and_stocks_id)
VALUES (1, 'url', 'title', 'url', 1),
       (2, 'url', 'title', 'url', 1),
       (3, 'url', 'title', 'url', 1);

insert into contact_cinema(id, address, location, logo, name, status, contact_page_id)
VALUES (1, 'address', 'location', 'logo', 'name', true, 1),
       (2, 'address', 'location', 'logo', 'name', true, 1);

insert into schedule(id, price, price_vip, time_session, cinema_id, film_id, hall_id)
VALUES (1, 100, 200, '2020-01-01 10:00:00', 1, 1, 1),
       (2, 100, 200, '2020-01-01 11:00:00', 1, 1, 1);




