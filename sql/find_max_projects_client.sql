SELECT name, count(project.id) as project_count
FROM client
JOIN project ON client.id = project.client_id
GROUP BY name
HAVING count(project.id) IN(
    SELECT count(project.id)
    FROM project
    GROUP BY client_id
    ORDER BY count(project.id) DESC
    LIMIT 1
);