package be.vinci.ipl.gateway;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

  public final GatewayService gatewayService;

  public GatewayController(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }
}
