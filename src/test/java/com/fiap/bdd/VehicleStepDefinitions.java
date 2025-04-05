package com.fiap.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.infrastructure.controller.dto.VehicleRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VehicleStepDefinitions {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    private MvcResult mvcResult;
    private VehicleRequest vehicleRequest;
    private Long vehicleId;

    @Given("eu tenho os dados de um veículo válido")
    public void eu_tenho_os_dados_de_um_veiculo_valido() {

        vehicleRequest = new VehicleRequest();
        vehicleRequest.setMarca("Fiat");
        vehicleRequest.setModelo("Uno");
        vehicleRequest.setAno(2022);
        vehicleRequest.setCor("Branco");
        vehicleRequest.setPreco(25000.0);

    }

    @When("eu envio uma requisição para criar o veículo")
    public void eu_envio_uma_requisicao_para_criar_o_veiculo() throws Exception {
        mvcResult = mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        vehicleId = objectMapper.readTree(responseContent).get("id").asLong();
        System.out.println("vehicleId" +  vehicleId);
    }

    @Then("a resposta deve ter status 200 e conter os dados do veículo criado")
    public void a_resposta_deve_ter_status_200_e_conter_os_dados_do_veiculo_criado() throws Exception {
        String responseContent = mvcResult.getResponse().getContentAsString();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(responseContent).contains("Fiat");
        assertThat(responseContent).contains("Uno");
        assertThat(responseContent).contains("Branco");
    }

    @Given("existe um veículo disponível cadastrado")
    public void existe_um_veiculo_disponivel_cadastrado() throws Exception {
        eu_tenho_os_dados_de_um_veiculo_valido();
        eu_envio_uma_requisicao_para_criar_o_veiculo();
    }

    @When("eu envio uma requisição para vender o veículo")
    public void eu_envio_uma_requisicao_para_vender_o_veiculo() throws Exception {
        mvcResult = mockMvc.perform(patch("/vehicles/" + vehicleId + "/sell"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Then("a resposta deve ter status 200 e status do veículo como {string}")
    public void a_resposta_deve_ter_status_200_e_status_do_veiculo_como(String expectedStatus) throws Exception {
        String responseContent = mvcResult.getResponse().getContentAsString();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(responseContent).contains(expectedStatus);
    }
}
