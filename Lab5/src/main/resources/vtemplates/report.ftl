<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${repoName} - Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<h1>${repoName} - Image Repository Report</h1>
<p>Generated on: ${.now?string("yyyy-MM-dd HH:mm:ss")}</p>

<table>
    <thead>
    <tr>
        <th>Image Name</th>
        <th>Tags</th>
        <th>File Size</th>
        <th>Last Modified</th>
    </tr>
    </thead>
    <tbody>
    <#list files as file>
        <tr>
            <td>${file.name}</td>
            <td>
                <#if file.tags?? && file.tags?size gt 0>
                  <ul style="margin: 0; padding-left: 20px; text-decoration: none;">
                    <#list file.tags as tag>
                      <li>${tag}</li>
                    </#list>
                  </ul>
                </#if>
            </td>
            <td>${file.size}</td>
            <td>${file.lastModified}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>