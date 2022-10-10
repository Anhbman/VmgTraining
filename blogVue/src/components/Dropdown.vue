<template>
  <div>
    <el-dropdown @command="handleCommand" szie="small">
      <el-button type="primary">
        {{selected? selected : dataDropdown.title}}<i class="el-icon-arrow-down el-icon--right"></i>
      </el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="">All</el-dropdown-item>
        <el-dropdown-item v-for="(item, index) in dataDropdown.data" :key="index" :command="item">{{item.name}}</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: 'dropdown',
  data() {
    return {
      selected: '',
    }
  },
  props: {
    dataDropdown: {}
  },
  methods: {
    handleCommand(val) {
      this.selected = val.name;
      this.$emit('clickDropdown', this.selected);
    }
  }
}
</script>