#language: pt
#encoding: iso-8859-1

Funcionalidade: Pesquisar um livros
como um usuário da loja de livros
eu quero pesquisar por um livro desejado
para que eu possa realizar uma compra

Esquema do Cenário: Pesquisa de livros com sucesso
Dado Acessar a página inicial da loja de livros
E Informar o nome do livro para pesquisa <livro>
Quando Solicitar a realização de pesquisa
Então Sistema exibe os resultados obtidos

Exemplos:
| livro               |
| "Fortaleza Digital" |
| "Caçador de pipas"  |
| "Coleção Java"      | 

Cenário: Nenhum livro encontrado para a pesquisa realizada
Dado Acessar a página inicial da loja de livros
E Informar o nome do livro para pesquisa "COTI"
Quando Solicitar a realização de pesquisa 
Então Sistema exibe a mensagem "Sua busca não retornou resultados."


