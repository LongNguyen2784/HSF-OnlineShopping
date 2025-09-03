package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.TerritoryDTO;
import shopping.onlineshopping.service.TerritoryService;

import java.util.List;

@RestController
@RequestMapping("/api/territories")
@RequiredArgsConstructor
public class TerritoryController {

    private final TerritoryService territoryService;

    // GET all territories
    @GetMapping
    public ResponseEntity<List<TerritoryDTO>> getAllTerritories() {
        return ResponseEntity.ok(territoryService.getAllTerritories());
    }

    // GET territory by ID
    @GetMapping("/{id}")
    public ResponseEntity<TerritoryDTO> getTerritoryById(@PathVariable Long id) {
        return ResponseEntity.ok(territoryService.getTerritoryById(id));
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<TerritoryDTO>> getTerritoriesByRegion(@PathVariable Long regionId) {
        return ResponseEntity.ok(territoryService.getTerritoriesByRegion(regionId));
    }

    // GET by description
    @GetMapping("/description")
    public ResponseEntity<TerritoryDTO> getTerritoryByDescription(@RequestParam String desc) {
        return ResponseEntity.ok(territoryService.getTerritoriesByDescription(desc));
    }

    // POST - Create
    @PostMapping
    public ResponseEntity<TerritoryDTO> createTerritory(@RequestBody TerritoryDTO dto) {
        return ResponseEntity.ok(territoryService.createTerritory(dto));
    }

    // PUT - Update
    @PutMapping
    public ResponseEntity<TerritoryDTO> updateTerritory(@RequestBody TerritoryDTO dto) {
        return ResponseEntity.ok(territoryService.updateTerritory(dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerritory(@PathVariable Long id) {
        territoryService.deleteTerritoryById(id);
        return ResponseEntity.noContent().build();
    }
}
