INSERT INTO posting.category (name, display_order, status, access_rule)
VALUES
    ('m3kong', 0, 1, 1),
    ('tech', 1, 1, 1),
    ('product', 2, 1, 1),
    ('blog', 3, 1, 1);


INSERT INTO posting.category_localization (content, language_code, status, category_id)
VALUES
    ('M3Kong', 'en', 1, 1),
    ('M3Kong', 'vi', 1, 1),
    ('Learn code', 'en', 1, 2),
    ('Học lập trình', 'vi', 1, 2),
    ('Product', 'en', 1, 3),
    ('Sản phẩm', 'vi', 1, 3),
    ('Blog', 'en', 1, 4),
    ('Blog', 'vi', 1, 4);