# RotiFlow — Wireframe Awal

**Status:** Draft v0.1 — perlu dibahas bersama sebelum implementasi UI.  
**Tujuan:** Menyamakan susunan layar dan alur kerja terlebih dahulu. Ini wireframe teks, belum desain final.

## Prinsip desain

- Utamakan layar Android portrait dan penggunaan satu tangan.
- Label bahasa Indonesia, ukuran tombol nyaman disentuh, angka mudah dibaca.
- Jangan menampilkan selisih sebagai stok fisik.
- Data contoh harus diberi label **DEMO**.
- Hindari grafik/animasi berat; ringkasan angka harus cepat dipahami.

## 1. Ringkasan

```text
┌────────────────────────────────┐
│ RotiFlow                 DEMO  │
│ Ringkasan                      │
│ Sabtu, 24 Oktober 2026     [⌄] │
├────────────────────────────────┤
│ PRODUKSI HARI INI               │
│ 1.250 buah                      │
├────────────────────────────────┤
│ DISTRIBUSI HARI INI             │
│ 1.080 buah                      │
├────────────────────────────────┤
│ SELISIH CATATAN                 │
│   170 buah                      │
│ Produksi dikurangi distribusi   │
│ Bukan angka stok fisik          │
├────────────────────────────────┤
│ [ + Catat Produksi ]            │
│ [ + Catat Distribusi ]          │
├────────────────────────────────┤
│ Aktivitas Terbaru      Lihat >  │
│ Produksi · Roti Cokelat         │
│ 300 buah · 08.30                │
│ Distribusi · Pedagang Demo A    │
│ 120 buah · 09.00                │
├────────────────────────────────┤
│ Ringkasan | Produksi | Distribusi│
└────────────────────────────────┘
```

Angka di atas hanya contoh tampilan, bukan data pabrik. Filter tanggal mengubah semua ringkasan sesuai tanggal yang dipilih.

## 2. Catat Produksi

```text
┌────────────────────────────────┐
│ ← Kembali     Catat Produksi    │
├────────────────────────────────┤
│ Tanggal produksi                │
│ [ 24/10/2026                 ]  │
│ Jenis/nama roti                 │
│ [ Roti Cokelat               ]  │
│ Jumlah diproduksi (buah)        │
│ [ 300                        ]  │
│ Catatan (opsional)              │
│ [ Shift pagi                 ]  │
│                                │
│ [       SIMPAN PRODUKSI      ]  │
├────────────────────────────────┤
│ Riwayat Produksi      Lihat >   │
│ Roti Cokelat · 300 buah         │
│ Roti Tawar · 450 buah           │
└────────────────────────────────┘
```

## 3. Catat Distribusi

```text
┌────────────────────────────────┐
│ ← Kembali    Catat Distribusi   │
├────────────────────────────────┤
│ Tanggal pengiriman              │
│ [ 24/10/2026                 ]  │
│ Nama pedagang                   │
│ [ Pedagang Demo A            ]  │
│ Jenis/nama roti                 │
│ [ Roti Cokelat               ]  │
│ Jumlah dikirim (buah)           │
│ [ 120                        ]  │
│ Catatan (opsional)              │
│ [ Rute pagi                  ]  │
│                                │
│ [      SIMPAN DISTRIBUSI     ]  │
├────────────────────────────────┤
│ Riwayat Distribusi    Lihat >   │
│ Pedagang Demo A · 120 buah      │
│ Pedagang Demo B · 180 buah      │
└────────────────────────────────┘
```

## 4. Riwayat

Riwayat dibuka melalui tautan **Lihat** pada bagian terkait. Sediakan filter tanggal dan pencarian nama roti; pada riwayat distribusi, sediakan juga filter/pencarian pedagang. Setiap baris menunjukkan tanggal, jenis roti, jumlah, dan waktu pencatatan. Mengedit atau menghapus catatan perlu konfirmasi dan harus memperbarui ringkasan.

## 5. Arah visual yang perlu dipilih

**A — Terang, profesional**
- Latar putih keabu-abuan.
- Teks biru tua, kartu putih, aksen warna gandum/oranye.
- Kesan rapi dan mudah dibaca di tempat kerja.

**B — Gelap, modern**
- Latar biru gelap/arang.
- Kartu sedikit lebih terang, teks putih, aksen hijau mint.
- Kesan dashboard modern; perlu diperiksa keterbacaannya di luar ruangan.

Rekomendasi awal: **A — Terang, profesional**, karena formulir dan angka akan sering dibaca. Warna dan susunan masih dapat diubah setelah dibahas.

## 6. Keputusan yang perlu disepakati sebelum coding UI

1. Arah visual: A atau B?
2. Apakah nama roti dan pedagang diketik bebas untuk demo, atau dipilih dari daftar?
3. Apakah beranda cukup memakai tiga ringkasan angka, atau perlu grafik sederhana?
4. Apakah catatan produksi/distribusi harus bisa diedit setelah tersimpan?

## Batasan penting

Prototipe ini mencatat produksi dan jumlah yang dikirim saja. Selisih produksi dan distribusi tidak boleh dianggap sebagai stok yang tersedia karena stok awal, retur, roti rusak, dan penyesuaian belum dicatat.