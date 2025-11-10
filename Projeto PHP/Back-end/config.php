<?php
define("GEMINI_API_URL", "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=");
define("GEMINI_API_KEY", "O Recruta ta achando que eu vou deixar a Chave aqui?");

$host = 'localhost';
$user = 'root';
$password = '';
$database = 'kowalski_db';

/*
PDO = PHP Data Object
Representa uma Conexão Ativa entre um DB e o Código PHP
*/
try {
    /*
    mysql: Usa o Driver MySQL
    host: Conecta ao Banco de Dados usando o hostname Atribuido a Variável $host
    dbname: Seleciona o Banco de Dados
    charset: Utiliza um Padrão em Todas as Comunicações com o DB
    $user e $password: Credenciais do MySQL
    */
    $pdo = new PDO("mysql:host=$host;dbname=$database;charset=utf8", $user, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); // Tratamento de Erros com PDO Exception
} catch(PDOException $e) { // Se a Conexão Falhar, Pega o Erro e o Armazena na Variável $e
    die("Connection Failed: " . $e->setMessage()); // Termina a Conexão com o Motivo da Falha
}
?>