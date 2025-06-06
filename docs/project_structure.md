# project_structure

``` text
.                                # プロジェクトルート
├── deps.edn
├── docs
│   └── project_structure.md
├── README.md
└── src
    ├── app
    │   ├── adapter
    │   │   ├── in
    │   │   │   └── cli.clj
    │   │   └── out
    │   │       └── writer.clj
    │   ├── core
    │   │   ├── domain
    │   │   │   └── domain.clj
    │   │   ├── port
    │   │   │   └── port.clj
    │   │   └── service
    │   │       ├── extractor.clj
    │   │       ├── ocr_service.clj
    │   │       └── pdf.clj
    │   └── entry
    │       └── point.clj           # エントリーポイント
    └── resource
```