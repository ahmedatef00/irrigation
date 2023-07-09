package com.example.irrigation.controllers;

import com.example.irrigation.dto.request.ConfigurationProcess;
import com.example.irrigation.dto.request.PlotRequest;
import com.example.irrigation.dto.response.ConfigurationProcessResponse;
import com.example.irrigation.dto.response.PlotResponse;
import com.example.irrigation.services.PlotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plots")
public class PlotController {

  private final PlotService plotService;

  @Autowired
  public PlotController(PlotService plotService) {
    this.plotService = plotService;
  }

  @PostMapping
  public PlotResponse addPlot(@RequestBody PlotRequest plot) {
    return plotService.addPlot(plot);
  }

  @PutMapping("/{plotId}")
  public ConfigurationProcessResponse configurePlot(
      @PathVariable Long plotId, @RequestBody ConfigurationProcess configurationProcess) {
    return plotService.configurePlot(plotId, configurationProcess);
  }

  @PatchMapping("/{plotId}")
  public PlotResponse editPlot(@PathVariable Long plotId, @RequestBody PlotRequest plot) {
    return plotService.editPlot(plotId, plot);
  }

  @GetMapping
  public List<PlotResponse> getAllPlots() {
    return plotService.getAllPlots();
  }

  @GetMapping("/{plotId}")
  public PlotResponse getPlot(@PathVariable Long plotId) {
    return plotService.getPlot(plotId);
  }
}
