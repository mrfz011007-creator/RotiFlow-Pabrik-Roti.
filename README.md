# RotiFlow — Produksi & Distribusi Roti

RotiFlow adalah prototipe aplikasi Android untuk mendemonstrasikan pencatatan produksi roti dan distribusi kepada pedagang.

> **Status:** Tahap desain/implementasi prototipe. Belum untuk pencatatan operasional pabrik.

## Fokus
- Mencatat jumlah roti yang diproduksi.
- Mencatat jumlah roti yang dikirim kepada setiap pedagang.
- Menampilkan ringkasan dan riwayat catatan.
- Menggunakan data demo sampai alur kerja dikonfirmasi dengan pihak pabrik.

## Dokumen
- [Desain aplikasi](docs/DESIGN.md)
- [Roadmap implementasi](docs/ROADMAP.md)

## Batasan awal
Versi pertama tidak mencakup retur, roti rusak, bahan baku, keuangan, stok fisik lintas hari, login, atau sinkronisasi antarperangkat. Semua itu perlu divalidasi lebih dahulu.

## Rencana teknis
Target: aplikasi Android native, penyimpanan lokal untuk prototipe, dan build APK debug otomatis melalui GitHub Actions. Build yang berhasil tetap harus diuji pada perangkat Android sebelum didemonstrasikan.

Jangan gunakan prototipe sebagai catatan operasional tunggal sebelum proses, keamanan data, backup, dan kebutuhan multi-perangkat selesai dirancang serta diuji.
