package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Ent�o;

public class PesquisarLivrosTest {
	
	WebDriver driver; 
	
	@Dado("^Acessar a p�gina inicial da loja de livros$")
	public void acessar_a_p�gina_inicial_da_loja_de_livros() {
	    
		System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("http://www.lojaexemplodelivros.com.br/");
		
		driver.manage().window().maximize();
	}

	@Dado("^Informar o nome do livro para pesquisa \"([^\"]*)\"$")
	public void informar_o_nome_do_livro_para_pesquisa(String arg1) {
	    
		driver.findElement(By.cssSelector("#search")).sendKeys(arg1);
	}

	@Quando("^Solicitar a realiza��o de pesquisa$")
	public void solicitar_a_realiza��o_de_pesquisa() {
	    
		driver.findElement(By.cssSelector("#search_mini_form > div > button")).click();
	}

	@Ent�o("^Sistema exibe os resultados obtidos$")
	public void sistema_exibe_os_resultados_obtidos() {
	    
		//capturar o endere�o da p�gina para onde o usu�rio foi redirecionado
		String pagina = driver.getCurrentUrl();
		
		//comparando se o usu�rio foi redirecionado para a p�gina
		//que mostra os resultados da pesquisa realizada.
		assertTrue(pagina.contains("catalogsearch/result"));
		
		try {			
			String data = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());			
			File arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(arquivo, new File("c:\\evidencias\\pesquisar_livros_" + data + ".png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		//fechando o navegador
		driver.close();
		driver.quit();
	}

	@Ent�o("^Sistema exibe a mensagem \"([^\"]*)\"$")
	public void sistema_exibe_a_mensagem(String arg1) {
	    
		//obtendo a mensagem exibida pelo sistema quando nenhum livro � encontrado
		String mensagem = driver.findElement(By.cssSelector("body > div > div > div.main-container.col2-left-layout > div > div.col-main > p")).getText();
		
		//comparando a mensagem obtida com a mensagem descrita na feature
		assertEquals(mensagem, arg1);
		
		try {			
			String data = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());			
			File arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(arquivo, new File("c:\\evidencias\\pesquisar_livros_" + data + ".png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		//fechando o navegador
		driver.close();
		driver.quit();
	}
}
