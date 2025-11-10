#include <iostream>
#include <string.h>
#include <locale.h>
#include <windows.h>
#include <vector>
#include <string>

#define PEDIDO "Pedido.txt"

using namespace std;

void criarPedido() {
    /*
    Cria uma Variável do Tipo Arquivo, * é o Ponteiro para a Variável, fp é uma Convenção de Nomenclatura, Significa File Pointer
    fopen() = Função para Abrir um Arquivo, Aceita 2 Parâmetros, Nome do Arquivo, Tipo de Manipulação que será feita no Arquivo
    a = append
    r = read
    w = write
    */
    FILE *fp = fopen(PEDIDO, "a");
    if(fp == NULL) /* Verifica se o Arquivo Existe, se Não, Retorna */ {
        cout << "Erro ao Criar um Novo Pedido." << endl;
        return;
    }
    fclose(fp); // Fecha o Arquivo
}
void adicionarItem(char item[20]) {
    FILE *fp = fopen(PEDIDO, "a");
    if(fp == NULL) {
        cout << "Erro ao Abrir o Pedido." << endl;
        return;
    }
    /*
    fprintf() = Formatted Print to File
    Função que Adiciona o Item ao Arquivo Pedido.txt
    Aceita 3 Parâmetros (Ponteiro do Arquivo, Formato, Valores)
    */
    fprintf(fp, "%s;\n", item);
    fclose(fp);
    cout << "Pedido Adicionado com Sucesso!" << endl;
}
void visualizarPedido() {
	FILE *fp = fopen(PEDIDO, "r");
	char linha[100]; // Cria um Array de Caracteres com um Valor Acima do Necessário para Percorrer uma "Linha" no Arquivo
	int i = 1; // Inicialização de uma Variável para Iteração
	if(fp == NULL) {
        cout << "Erro ao Abrir o Pedido." << endl;
        return;
    }
    cout << "Pedido:" << endl;
    /*
    fgets*() = File Get String
    Lê uma Linha até a Quebra (Inclui o Valor de Quebra \0)
    Parâmetros: (Variável que Recebera o Valor, Tamanho, Ponteiro do Arquivo)
    */
    while(fgets(linha, sizeof(linha), fp)) {
    	cout << i++ << ". " << linha; // Lista os Itens do Pedido e Incrementa o Valor da Variável i, Criando uma Lista Ordenada
	}
	fclose(fp);
}
void alterarItem() {
    FILE *fp = fopen(PEDIDO, "r");
    if(fp == NULL) {
        cout << "Erro ao Abrir o Pedido." << endl;
        return;
    }
    vector<string> itens; // Cria um Vetor Dinâmico do Tipo String para facilitar a Vizualização e Manipulação dos Itens do Pedido
    char linha [100];
    while(fgets(linha, sizeof(linha), fp)) {
        string item = linha; // Converte o Array Linha em uma String para Facilitar a Manipulação
        if(!item.empty()) /* Verificação Impede de Adicionar Linhas em Branco */ {
            itens.push_back(item); // Adiciona a String item ao Final do Vetor itens
        }
    }
    fclose(fp);

    if(itens.empty()) {
        cout << "Nenhum Item Encontrado para Alterar." << endl;
        return;
    }
    cout << "Itens Atuais do Pedido:" << endl << endl;
    /* size_t Sinaliza que uma Variável do Tipo Inteiro que tem como Propósito a Contagem */
    for (size_t i = 0; i < itens.size(); i++) {
        cout << i + 1 << ". " << itens[i]; // Lista os Itens do Pedido em uma Lista Ordenada
    }

    int pos; // Inicia uma Variável do Tipo int para Armazenar o Número do Item que Será Alterado/Excluido
    cout << "Digite o Número do Item que Deseja Alterar: ";
    cin >> pos;
    cin.ignore();
    if(pos < 1 || pos > (int)itens.size()) {
        cout << "Item Inválido" << endl;
        return;
    }

    string novoItem;
    cout << "Digite o Novo Item a ser Adicionado: ";
    getline(cin, novoItem); // Lê a Entrada do Uusuário e a Armazena na String novoItem
    itens[pos - 1] = novoItem + ";\n";

    /*
    Reescreve o Pedido utilizando os Valores do Vetor itens
    auto Descobre Automaticamente o Tipo da Variável
    &it Significa Referência (Acessa o Item Diretamente sem Copia-lo)
    : itens Percorre cada Elemento do Vetor itens, Equivalente a um Loop For
    */
    fp = fopen(PEDIDO, "w");
    for (auto &it : itens) {
        /*
        fputs = File Put String, Escreve uma String dentro de um Arquivo
        Recebe Dois Parâmetros: String a Ser Gravada, Ponteiro do Arquivo
        c_str() = Função que Converte uma std::string para const char(Necessário para Passagem de Parâmetro da Função fputs
        */
        fputs(it.c_str(), fp);
    }
    fclose(fp);
    cout << "Item Alterado com Sucesso!" << endl;
}
void excluirItem() {
    FILE *fp = fopen(PEDIDO, "r");
    if(fp == NULL) {
        cout << "Erro ao Abrir o Pedido." << endl;
        return;
    }

    vector<string> itens;
    char linha[100];
    while(fgets(linha, sizeof(linha), fp)) {
        string item = linha;
        if(!item.empty()) {
            itens.push_back(item);
        }
    }
    fclose(fp);

    if(itens.empty()) {
        cout << "Nenhum Item Encontrado para Excluir." << endl;
        return;
    }
    cout << "Itens Atuais do Pedido:" << endl << endl;
    for (size_t i = 0; i < itens.size(); i++) {
        cout << i + 1 << ". " << itens[i];
    }
    int pos;
    cout << "Digite o Número do Item que Deseja Excluir: ";
    cin >> pos;
    cin.ignore();
    if(pos < 1 || pos > (int)itens.size()) {
        cout << "Item Inválido" << endl;
        return;
    }
    itens.erase(itens.begin() + pos - 1);

    fp = fopen(PEDIDO, "w");
    for(auto &it : itens) {
        fputs(it.c_str(), fp);
    }
    fclose(fp);
    cout << "Item Excluido com Sucesso!" << endl;
}

int main()
{
    setlocale(LC_ALL, "Portuguese"); // Define Localização para Português

    char username[20], password[20], item[20]; // Cria Arrays de Chars com Tamanho de 20 Caracteres para o Nome de Usúario, Senha e Item
    // Usuário e Senha Padrão para Testes pela Equipe de Desenvolvimento
    char adminUser[] = "admin";
    char adminPassword[] = "1234";
    int authentication = 0; // Variável Lógica para Autenticação de Usuário

    int option; // Variável do Tipo Inteiro, Inicializada sem Valor para Armazenar a Opção Escolhida pelo Usuário

    cout << "Bem Vindo(a)!" << endl << endl;
    cout << "Para continuar, faça o seu Login" << endl;
    cout << endl;
    do /* Loop Do While onde a Condição para Sair do Loop é o Estado da Variável authenticaion */ {
        system("pause"); // Pausa o Sistema, Forçando o Usuário a Apertar Enter para Prosseguir
        system("cls"); // Limpa as Informações Anteriores da Tela
        // Tela de Autenticação do Usuário
        cout << "Nome de Usuário: ";
        cin >> username;
        cout << "Senha: ";
        cin >> password;
        cout << endl;
        /*
        Compara as Strings username com adminUser e password com adminPassword, Caractere por Caractere
        Se as Strings forem iguals, o Valor Retornado será 0
        Se a String username for menor que a String adminUser (Em Termos de Ordem Alfabética), o Valor Retornado será Menor que 0
        Caso Contrário, o Valor Retornado será Maior que 0
        */
        if(strcmp(username, adminUser) == 0 && strcmp(password, adminPassword) == 0) {
            cout << "Login realizado com sucesso! " << endl << "Bem vindo " << username << endl << endl;
            authentication = 1; // Atualiza o Valor Lógico para 1, True
        } else {
            cout << "Senha ou Usuário Inválidos" << endl;
            authentication = 0; // Atualiza o Valor Lógico para 0, False. Pode ser útil Futuramente para Implementar uma Função de Logoff
        }
    } while (authentication != 1);
    do /* Loop Do While, Lista Todas as Funções Disponiveis enquanto a Opção Escolhida for Diferente da Opção 5. Sair */ {
        system("pause");
        system("cls");
        cout << "O que deseja fazer?" << endl << endl;
        cout << "1. Incluir Item no Pedido" << endl;
        cout << "2. Visualizar Pedido" << endl;
        cout << "3. Alterar Item no Pedido" << endl;
        cout << "4. Excluir Item no Pedido" << endl;
        cout << "5. Sair" << endl << endl;
        cout << "Digite uma opção: ";
        cin >> option;

        switch(option) {
        case 1:
            system("cls");
            cout << "Digite seu Pedido: ";
            cin >> item; // Atualiza o Valor da Variável Pedido
            criarPedido(); // Cria um Arquivo de Texto em Branco com o Nome Pedido
            adicionarItem(item); // Adiciona um Item a Lista do Pedido, passando o Valor da Variável item como Parâmetro
            break;
        case 2:
            system("cls");
            visualizarPedido(); // Visualiza Itens Gravados no Arquivo Pedido.txt
            break;
        case 3:
            system("cls");
            alterarItem(); // Altera Itens Existentes no Arquivo Pedido.txt
            break;
        case 4:
            system("cls");
            excluirItem(); // Exclui Itens Existentes no Arquivo Pedido.txt
            break;
        case 5: // Finaliza o Programa
            system("cls");
            cout << "Obrigado por utilizar nossos Serviços." << endl;
            break;
        default:
            system("cls");
            cout << "Opção Inválida, digite um Número entre 1 à 5." << endl << endl;
            break;
        }
    } while (option != 5);

    return 0;
}
// Desenvolvido por Cassiano, Gianlucca e Melissa
