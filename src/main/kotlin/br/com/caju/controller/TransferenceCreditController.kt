package br.com.caju.controller
import br.com.caju.controller.dto.*
import br.com.caju.service.*
import br.com.caju.domain.model.*

@RestController
@RequestMapping("/transferenceCredit")
@Tag(name = "TransferenceCredit Controller", description = "RESTful API for managing transference.")
class TransferenceCreditController(private val transferenceCreditService: ITransferenceCreditService) {

    @PostMapping
    @Operation(summary = "Create a new transference", description = "Create a new transference and return the code")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Transference created successfully"),
    ])
    fun create(@RequestBody transferenceCreditRequestDto: TransferenceCreditRequestDto): ResponseEntity<TransferenceCreditRequestDto> {
        val response = this.transferenceCreditService.transferenceCredit(transferenceCreditRequestDto.toModel())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response)
                .toUri()
        return ResponseEntity.created(location).body(TransferenceCreditResponseDto(response))
    }
}
