select img_id,
       img_url,
       img_bg_color,
       prod_id,
       category_id,
       index_type,
       seq,
       status,
       create_time,
       update_time
from index_img
where status = 1
order by seq