#language: pt
#encoding: iso-8859-1

Funcionalidade: Pesquisar um livros
como um usu�rio da loja de livros
eu quero pesquisar por um livro desejado
para que eu possa realizar uma compra

Esquema do Cen�rio: Pesquisa de livros com sucesso
Dado Acessar a p�gina inicial da loja de livros
E Informar o nome do livro para pesquisa <livro>
Quando Solicitar a realiza��o de pesquisa
Ent�o Sistema exibe os resultados obtidos

Exemplos:
| livro               |
| "Fortaleza Digital" |
| "Ca�ador de pipas"  |
| "Cole��o Java"      | 

Cen�rio: Nenhum livro encontrado para a pesquisa realizada
Dado Acessar a p�gina inicial da loja de livros
E Informar o nome do livro para pesquisa "COTI"
Quando Solicitar a realiza��o de pesquisa 
Ent�o Sistema exibe a mensagem "Sua busca n�o retornou resultados."


