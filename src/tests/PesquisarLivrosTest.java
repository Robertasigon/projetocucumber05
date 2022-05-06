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
import cucumber.api.java.pt.Então;

public class PesquisarLivrosTest {
	
	WebDriver driver; 
	
	@Dado("^Acessar a página inicial da loja de livros$")
	public void acessar_a_página_inicial_da_loja_de_livros() {
	    
		System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("http://www.lojaexemplodelivros.com.br/");
		
		driver.manage().window().maximize();
	}

	@Dado("^Informar o nome do livro para pesquisa \"([^\"]*)\"$")
	public void informar_o_nome_do_livro_para_pesquisa(String arg1) {
	    
		driver.findElement(By.cssSelector("#search")).sendKeys(arg1);
	}

	@Quando("^Solicitar a realização de pesquisa$")
	public void solicitar_a_realização_de_pesquisa() {
	    
		driver.findElement(By.cssSelector("#search_mini_form > div > button")).click();
	}

	@Então("^Sistema exibe os resultados obtidos$")
	public void sistema_exibe_os_resultados_obtidos() {
	    
		//capturar o endereço da página para onde o usuário foi redirecionado
		String pagina = driver.getCurrentUrl();
		
		//comparando se o usuário foi redirecionado para a página
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

	@Então("^Sistema exibe a mensagem \"([^\"]*)\"$")
	public void sistema_exibe_a_mensagem(String arg1) {
	    
		//obtendo a mensagem exibida pelo sistema quando nenhum livro é encontrado
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
