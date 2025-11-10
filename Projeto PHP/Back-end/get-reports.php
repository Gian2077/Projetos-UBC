<?php
/* Endpoint da API em PHP */
require 'config.php'; // Torna $pdo Disponivel para ser Utilizado
/*
stmt = Statement
PDOStatement Object
*/
$stmt = $pdo->query("SELECT * FROM reports ORDER by created_at DESC LIMIT 10"); // Executa uma Consulta SQL que Retorna Todas as Colunas da Tabela Reports e as Organiza de Forma Decrescente, Limitando os Resultados em 10
/*
Busca Todas as Linhas da Consulta e as TRansforma em um Array Associativo (Nome das Colunas se Tornam a Chave do Array)
json_enconde() Converte o Array Associativo em uma String JSON
echo Envia o Arquivo JSON em Forma de uma Resposta HTTP
*/
echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
?>