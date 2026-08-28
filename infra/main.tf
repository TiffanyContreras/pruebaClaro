terraform {
  required_version = ">= 1.6.0"

  required_providers {
    local = {
      source  = "hashicorp/local"
      version = "~> 2.5"
    }
  }
}

provider "local" {
}

resource "local_file" "infra_info" {
  filename = "${path.module}/infra_generada.txt"

  content = <<EOT
Proyecto: ${var.project_name}
Microservicio: ${var.service_name}
Puerto: ${var.service_port}
Base de datos: ${var.database}
Despliegue: ${var.deployment_platform}
EOT
}