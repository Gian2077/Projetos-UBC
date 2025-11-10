<?php
require 'config.php';

$url = GEMINI_API_URL . GEMINI_API_KEY;
$prompt = $_POST['prompt'];
/*
Prepara o Corpo da Requisição HTTP
Codifica o Prompt em uma String JSON, no Formato Aceito pelo Gemini
contents: Array de Objetos com Mensagens
Cada Mensagem é composta por parts, e cada part tem text
*/
$data = json_encode([
    'contents' => [
        [
            'parts' => [
                ['text' => "$prompt, sempre comece a resposta com a frase 'Sim Capitão! Aqui está o relatório sobre: Tema Central da Pergunta'. Depois forneça uma resposta detalhada e informativa em português brasileiro. Seja claro, conciso e direto ao ponto. Utilize uma linguagem formal e profissional, adequada para um relatório técnico. Evite jargões desnecessários e explique termos técnicos quando apropriado. Estruture a resposta em parágrafos bem organizados, garantindo que cada seção aborde um aspecto específico do tema central. Inclua exemplos relevantes para ilustrar pontos importantes e facilite a compreensão do leitor. Finalize o relatório com uma conclusão que resuma os principais pontos discutidos e ofereça recomendações ou considerações finais, se aplicável."]
            ]
        ]
    ]
]);
/*
Exemplo de Saida no Formato JSON:
{
    "contents": [{
        "parts": [{
            "text": O que é PHP? ...
        }]
    }]
}
*/

$ch = curl_init($url); // ch = cURL Handle, curl_init() Inicializa uma Sessão cURL na URL especificada
curl_setopt($ch, CURLOPT_URL, $url); // Seta a URL de Destino
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); // Retorna a Resposta da API como uma String ao Invés de Imprimi-la
curl_setopt($ch, CURLOPT_POST, true); // Seta o Metódo da Requisição para POST
curl_setopt($ch, CURLOPT_HTTPHEADER, /* Seta o Header da Requisição HTTP */[
    'Content-Type: application/json',
    'x-goog-api-key: ' . GEMINI_API_KEY
]);
curl_setopt($ch, CURLOPT_POSTFIELDS, $data); // Anexa a Carga (payload) $data no Corpo da Requisição POST
$response = curl_exec($ch); // Executa a Requisição cURL e Salva a Resposta na Variável response
if(curl_errno($ch))/* Trata Erros */ {
    echo 'cURL Error: ' . curl_error($ch);
} else {
    // $report = json_decode($response, true);
    $responseText = json_decode($response, true); // Decodifica a String JSON em um Array Associativo PHP
}
// if(isset($report['candidates'][0]['content']['parts'][0]['text'])) {
//     echo $report['candidates'][0]['content']['parts'][0]['text'];
// } else {
//     echo "O Rico comeu os cabos de novo Capitão.";
// }
if(isset($responseText['candidates'][0]['content']['parts'][0]['text'])) {
    $report = $responseText['candidates'][0]['content']['parts'][0]['text'];
    if(!empty($prompt) && !empty($report)) {
        $stmt = $pdo->prepare("INSERT INTO reports (prompt, response) VALUES (:prompt, :response)");
        $stmt->execute([
            ':prompt' => $prompt,
            ':response' => $report
        ]);
    }
    echo $report;
} else {
    echo "O Rico comeu os cabos de novo Capitão.";
}
curl_close($ch);
?>