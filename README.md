# RotiFlow — Pesanan Harian Pabrik Roti

RotiFlow adalah prototipe Android native untuk mencatat pesanan harian pabrik roti berdasarkan pedagang.

> **Status:** Fitur pesanan versi awal sudah diimplementasikan dan sedang melalui verifikasi build serta pengujian perangkat. Jangan gunakan sebagai satu-satunya catatan operasional sebelum diuji dan kebutuhan pencadangan ditinjau.

## Fitur prototipe

- Tanggal pesanan default hari ini dan dapat memilih tanggal lain.
- Pilih nama pedagang yang tersimpan atau ketik nama pedagang baru.
- Catat jumlah untuk 15 jenis roti dengan input angka bulat nonnegatif; nilai kosong disimpan sebagai 0.
- Simpan beberapa catatan terpisah untuk pedagang yang sama pada hari yang sama.
- Daftar pesanan per tanggal, rincian pesanan, total per pedagang, dan total otomatis per jenis roti.
- Edit pesanan tanggal lama dan hapus dengan konfirmasi.
- Nama pedagang dan pesanan tersimpan lokal di satu perangkat melalui SQLite.

## Jenis roti

Srikaya, Kelapa, Paha ayam, Donat buat, Moca, Abon, Keju, Mantul, Donat panjang, Kacang hijau, Pisang coklat, Seres warna, Cappucino, Nanas, dan Manis.

## Batasan

Data tidak disinkronkan ke cloud. Backup/restore belum termasuk versi awal, sehingga data hanya ada di perangkat dan berisiko hilang jika data aplikasi dihapus atau aplikasi di-uninstall. Jangan jadikan APK debug sebagai satu-satunya salinan data operasional.

Fitur produksi aktual, stok, roti rusak/retur/sisa, keuangan, pembayaran/utang, bahan baku/resep, login, multi-perangkat, dan menyalin pesanan hari sebelumnya berada di luar ruang lingkup.

## Dokumen

- [Desain aplikasi](docs/DESIGN.md)
- [Wireframe](docs/WIREFRAME.md)
- [Roadmap implementasi dan pengujian](docs/ROADMAP.md)

## Build APK

Workflow GitHub Actions membangun APK debug dan memublikasikan asset `RotiFlow-debug.apk` pada GitHub Release. Build yang berhasil bukan pengganti uji fungsi di perangkat Android.
