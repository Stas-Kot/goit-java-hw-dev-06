SELECT id as name, DATEDIFF('MONTH', project.start_date, project.finish_date) as month_count
FROM project
WHERE DATEDIFF('MONTH', project.start_date, project.finish_date) IN(
    SELECT DATEDIFF('MONTH', project.start_date, project.finish_date) as month_count
    FROM project
    ORDER BY month_count DESC
    LIMIT 1
);