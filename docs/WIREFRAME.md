# RotiFlow — Wireframe Prototipe Pesanan Harian

**Versi:** 0.2  
**Status:** Draft berdasarkan keputusan desain pengguna  
**Acuan utama:** [DESIGN.md](DESIGN.md)  
**Catatan:** Wireframe teks ini menjelaskan susunan layar, bukan tangkapan layar atau UI yang sudah diimplementasikan.

## 1. Navigasi utama

- **Pesanan / Riwayat** — daftar pesanan untuk tanggal terpilih dan total semua roti; pengguna dapat memilih tanggal lama untuk membuka riwayat.
- **Tambah pesanan** — input pesanan seorang pedagang.
- Tidak perlu halaman produksi aktual, distribusi aktual, keuangan, atau stok untuk versi awal.

## 2. Layar Pesanan Harian

```text
┌──────────────────────────────────┐
│ RotiFlow                         │
│ Pesanan Harian                   │
├──────────────────────────────────┤
│ Tanggal                          │
│ [ 24 Okt 2026              ▼ ]   │  ← default hari ini; dapat diubah
│                                  │
│ [ + Tambah Pesanan Pedagang ]    │
├──────────────────────────────────┤
│ PESANAN PEDAGANG                 │
│                                  │
│ Nama Pedagang A          120 roti│
│ [Buka rincian]                   │
│ ──────────────────────────────── │
│ Nama Pedagang B           85 roti│
│ [Buka rincian]                   │
│ ──────────────────────────────── │
│ Nama Pedagang C           60 roti│
│ [Buka rincian]                   │
├──────────────────────────────────┤
│ TOTAL PER JENIS ROTI             │
│ Srikaya                  25      │
│ Kelapa                   20      │
│ Paha ayam                15      │
│ ... lanjutkan 15 jenis ...       │
└──────────────────────────────────┘
```

**Perilaku:**
- Tanggal awal adalah tanggal hari ini.
- Pengguna dapat memilih tanggal lain untuk melihat riwayat, menambah pesanan, atau mengedit pesanan pada tanggal tersebut.
- Jika tanggal yang dipilih belum memiliki pesanan, tampilkan pesan bahwa belum ada pesanan pada tanggal tersebut dan tetap izinkan pengguna menambahkan pesanan.
- Daftar berisi catatan pesanan pada tanggal terpilih. Nama pedagang yang sama dapat muncul lebih dari sekali jika ada beberapa pesanan terpisah.
- Setiap baris memperlihatkan nama pedagang dan jumlah total rotinya.
- Baris/rincian dapat dibuka untuk melihat semua 15 jenis roti.
- Total per jenis roti ditampilkan di bawah daftar pedagang; tidak ada halaman rekap terpisah pada tahap awal.
- Angka di wireframe hanyalah contoh ilustrasi, bukan data pabrik.

## 3. Layar Tambah/Edit Pesanan Pedagang

```text
┌──────────────────────────────────┐
│ ← Kembali      Pesanan Pedagang  │
├──────────────────────────────────┤
│ Tanggal                          │
│ [ 24 Okt 2026              ▼ ]   │
│                                  │
│ Nama pedagang                    │
│ [ Pilih nama tersimpan       ▼ ] │
│ [ + Tambah nama pedagang       ] │
├──────────────────────────────────┤
│ JENIS ROTI              JUMLAH   │
│ Srikaya                 [   0  ] │
│ Kelapa                  [   0  ] │
│ Paha ayam               [   0  ] │
│ Donat buat              [   0  ] │
│ Moca                    [   0  ] │
│ Abon                    [   0  ] │
│ Keju                    [   0  ] │
│ Mantul                  [   0  ] │
│ Donat panjang           [   0  ] │
│ Kacang hijau            [   0  ] │
│ Pisang coklat           [   0  ] │
│ Seres warna             [   0  ] │
│ Cappucino               [   0  ] │
│ Nanas                   [   0  ] │
│ Manis                   [   0  ] │
├──────────────────────────────────┤
│ Total pesanan: 0 buah            │
│                                  │
│ [ Simpan Pesanan ]               │
│ [ Batal ]                        │
└──────────────────────────────────┘
```

**Aturan input:**
- Nama pedagang dipilih dari nama yang tersimpan atau nama baru ditambahkan.
- Nama pedagang saja; tidak ada kolom keterangan tambahan.
- Jumlah hanya menerima angka bulat nonnegatif; gunakan papan ketik numerik.
- Huruf, tanda minus, desimal, dan simbol tidak valid.
- Kolom kosong diperlakukan sebagai 0 ketika disimpan.
- Total pesanan pedagang dihitung otomatis.
- Jenis roti sudah tetap; pengguna tidak perlu mengetik nama roti.

## 4. Rincian Pesanan

Saat membuka baris pedagang, tampilkan tanggal, nama pedagang, rincian 15 jenis roti dan jumlahnya, total seluruh roti, serta tindakan **Edit** dan **Hapus**.

- Edit dapat dilakukan untuk tanggal apa pun.
- Hapus harus meminta konfirmasi.
- Setelah edit atau hapus, hitung ulang daftar dan total untuk tanggal yang sama.
- Beberapa pesanan terpisah dari pedagang yang sama pada tanggal yang sama diperbolehkan; setiap catatan memiliki rincian dan tindakan edit/hapus sendiri.
- Jangan mengubah tanggal tanpa tindakan pengguna yang jelas.

## 5. Gaya visual

- Terang dan profesional.
- Latar terang/netral dan teks berkontras tinggi.
- Tabel sederhana, mudah dibaca pada layar HP.
- Tombol cukup besar dan label berbahasa Indonesia.
- Tidak menggunakan animasi berat.

## 6. Status dan batasan

- Ini wireframe, bukan implementasi.
- Data demo harus diberi label DEMO.
- Data prototipe disimpan lokal pada satu HP; belum ada login atau sinkronisasi.
- Cadangan/pemulihan data belum diputuskan.
- Beberapa pesanan terpisah dari pedagang yang sama pada tanggal yang sama diperbolehkan; setiap catatan tampil sebagai baris terpisah dan memiliki rincian serta tindakan masing-masing.
